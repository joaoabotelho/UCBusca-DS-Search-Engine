package com.botelho.rmi.server.multicast;

import com.botelho.commons.CustomDatagramPacket;
import com.botelho.commons.MulticastRequest;
import com.botelho.commons.MulticastResponse;
import com.botelho.rmi.server.RmiServerImpl;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import static com.botelho.commons.Configuration.MULTICAST_HOST;
import static com.botelho.commons.Configuration.MULTICAST_PACKET_SIZE;
import static com.botelho.commons.Configuration.MULTICAST_PORT;
import static com.botelho.commons.Configuration.MULTICAST_TTL;

public class MulticastClient {
    private static Logger logger = LoggerFactory.getLogger(MulticastClient.class);

    private MulticastClient() {
    }

    private static MulticastSocket getNewSocket() {
        final MulticastSocket socket;
        try {
            socket = new MulticastSocket(MULTICAST_PORT);
            socket.joinGroup(InetAddress.getByName(MULTICAST_HOST));
            socket.setTimeToLive(MULTICAST_TTL);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Couldn't create a socket to host %s and port %d",
                    MULTICAST_HOST, MULTICAST_PORT), e);
        }
        return socket;
    }

    public static MulticastResponse communicate(MulticastRequest request) {

        byte[] serializedRequest = SerializationUtils.serialize(request);
        DatagramPacket packet = CustomDatagramPacket.build(serializedRequest, serializedRequest.length);
        MulticastSocket socket = getNewSocket();
        MulticastResponse response;
        try {
            socket.send(packet);
            logger.info("Waiting response.");
            response = waitResponse(socket, request);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't send the packet through multicast", e);
        } finally {
            socket.close();
        }

        return response;
    }

    private static boolean validResponse(MulticastRequest request, MulticastResponse response) {
        return request.getIdentifier() == response.getIdentifier();
    }

    private static MulticastResponse waitResponse(MulticastSocket socket, MulticastRequest request) throws IOException {
        MulticastResponse response;
        do {
            byte[] receiveBuffer = new byte[MULTICAST_PACKET_SIZE];
            DatagramPacket receivePacket = CustomDatagramPacket.build(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);

            response = SerializationUtils.deserialize(receiveBuffer);
        } while (validResponse(request, response));

        return response;
    }
}
