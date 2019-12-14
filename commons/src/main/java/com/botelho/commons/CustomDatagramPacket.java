package com.botelho.commons;

import com.botelho.commons.utils.NetworkUtils;

import java.net.DatagramPacket;

import static com.botelho.commons.Configuration.MULTICAST_HOST;
import static com.botelho.commons.Configuration.MULTICAST_PACKET_SIZE;
import static com.botelho.commons.Configuration.MULTICAST_PORT;

public class CustomDatagramPacket {
    private CustomDatagramPacket() {
    }

    public static DatagramPacket build(final byte[] bytes, final int length) {
        if (length <= MULTICAST_PACKET_SIZE) {
            return new DatagramPacket(bytes, length, NetworkUtils.getInetAddress(MULTICAST_HOST), MULTICAST_PORT);
        }
        throw new RuntimeException(String.format("Response can't be larger than %s",
                MULTICAST_PACKET_SIZE));
    }
}
