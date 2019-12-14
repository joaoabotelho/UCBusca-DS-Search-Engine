package com.botelho.rmi.server.routing;

import com.botelho.commons.RmiRequest;
import com.botelho.commons.RmiResponse;
import com.botelho.rmi.server.manager.auth.Auth;

public class Router {
    private Router() {
    }

    public static RmiResponse route(final RmiRequest rmiRequest) {
        switch (rmiRequest.getType()) {
            case LOGIN:
                return Auth.login(rmiRequest);

            case REGISTER:
                return Auth.register(rmiRequest);
        }

        return null;
    }
}
