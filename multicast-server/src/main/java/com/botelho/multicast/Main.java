package com.botelho.multicast;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new MulticastServer().startReceiving();
    }
}
