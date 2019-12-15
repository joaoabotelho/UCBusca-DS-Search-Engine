package com.botelho.rmi.server.routing;

import com.botelho.commons.RmiRequest;
import com.botelho.commons.RmiResponse;
import com.botelho.rmi.server.manager.auth.Auth;
import com.botelho.rmi.server.manager.pages.Search;

public class Router {
    private Router() {
    }

    public static RmiResponse route(final RmiRequest rmiRequest) {
        switch (rmiRequest.getType()) {
            case LOGIN:
                return Auth.login(rmiRequest);
            case SEARCH:
                return Search.searchByWords(rmiRequest);
            case REGISTER:
                return Auth.register(rmiRequest);
            case CREATEURL:
                return Admin.createUrl(rmiRequest);
            case PROMOTEUSER:
                return Admin.promoteUser(rmiRequest);
        }

        return null;
    }
}
