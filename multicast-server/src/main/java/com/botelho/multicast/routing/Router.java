package com.botelho.multicast.routing;

import com.botelho.commons.MulticastRequest;
import com.botelho.commons.MulticastResponse;
import com.botelho.multicast.auth.Auth;

public class Router {

    public static MulticastResponse route(MulticastRequest request) {
        switch (request.getActionType()) {
            case LOGIN:
                return Auth.getInstance().login(request);
        }

        return null;
    }
}
