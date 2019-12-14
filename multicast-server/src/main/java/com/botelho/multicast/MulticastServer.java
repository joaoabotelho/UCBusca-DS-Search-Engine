package com.botelho.multicast;

import com.botelho.commons.CustomDatagramPacket;
import com.botelho.commons.MulticastRequest;
import com.botelho.commons.MulticastResponse;
import com.botelho.multicast.routing.Router;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.botelho.commons.Configuration.MULTICAST_HOST;
import static com.botelho.commons.Configuration.MULTICAST_PACKET_SIZE;
import static com.botelho.commons.Configuration.MULTICAST_PORT;
import static com.botelho.commons.Configuration.MULTICAST_THREADS;
import static com.botelho.commons.Configuration.MULTICAST_TTL;

public class MulticastServer {
    private static Logger logger = LoggerFactory.getLogger(MulticastServer.class);
    private final MulticastSocket socket;
    private final ExecutorService executorService;

    public MulticastServer() {
        this.executorService = Executors.newFixedThreadPool(MULTICAST_THREADS);
        try {
            this.socket = new MulticastSocket(MULTICAST_PORT);
            this.socket.joinGroup(InetAddress.getByName(MULTICAST_HOST));
            this.socket.setTimeToLive(MULTICAST_TTL);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't start the multicast server.", e);
        }
        logger.info("Multicast Server successfully started.");
    }

    public void startReceiving() throws IOException {
        logger.info("Ready to receive messages");
        while (true) {
            final byte[] receiveBuffer = new byte[MULTICAST_PACKET_SIZE];
            DatagramPacket receivePacket = CustomDatagramPacket.build(receiveBuffer, receiveBuffer.length);
            logger.info("Waiting for requests");
            this.socket.receive(receivePacket);
            MulticastRequest request = SerializationUtils.deserialize(receiveBuffer);
            logger.info("Received {} request.", request.getActionType());

            submitWork(() -> {
                MulticastResponse response = Router.route(request);

                final byte[] responseBuffer = SerializationUtils.serialize(response);
                if (responseBuffer.length > MULTICAST_PACKET_SIZE) {
                    throw new RuntimeException(String.format("Response can't be larger than %s",
                            MULTICAST_PACKET_SIZE));
                }

                DatagramPacket responsePacket = CustomDatagramPacket.build(responseBuffer, responseBuffer.length);
                try {
                    this.socket.send(responsePacket);
                } catch (IOException e) {
                    throw new RuntimeException("Couldn't send response.", e);
                }
            });
        }
    }

    private void submitWork(Runnable work) {
        executorService.submit(work);
    }
}
