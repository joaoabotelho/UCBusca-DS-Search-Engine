package com.botelho.rmi.server.utils;

import com.botelho.commons.MulticastResponse;
import com.botelho.commons.ResponseStatus;
import com.botelho.commons.RmiResponse;

public class ResponseUtils {
    private ResponseUtils() {
    }

    public static RmiResponse multicastToRmi(MulticastResponse response) {
        return new RmiResponse(response.getStatus(), response.getData());
    }
}
