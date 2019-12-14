package com.botelho.commons.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static com.botelho.commons.Configuration.MULTICAST_HOST;

public class NetworkUtils {
    private NetworkUtils() {
    }

    public static InetAddress getInetAddress(String host) {
        try {
            return InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            throw new RuntimeException(String.format("Couldn't find InedAdress for host %s", MULTICAST_HOST));
        }
    }
}
