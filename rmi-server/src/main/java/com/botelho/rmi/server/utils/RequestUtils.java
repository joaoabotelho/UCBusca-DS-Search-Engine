package com.botelho.rmi.server.utils;

import com.botelho.commons.MulticastRequest;
import com.botelho.commons.RmiRequest;

public class RequestUtils {
    private RequestUtils() {
    }

    public static MulticastRequest rmiToMulticast(RmiRequest rmiRequest) {
        return new MulticastRequest(rmiRequest.getType(), rmiRequest.getData());
    }
}
