package com.botelho.rmi.server.manager.auth;

import com.botelho.commons.RmiRequest;
import com.botelho.commons.RmiResponse;
import com.botelho.commons.User;
import com.botelho.rmi.server.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.botelho.commons.ResponseStatus.FAILED;
import static com.botelho.commons.ResponseStatus.SUCCESS;

public class Auth {
    private static Logger logger = LoggerFactory.getLogger(Auth.class);

    private static final List<String> loggedInUsers = new ArrayList<>();

    private Auth() {
    }

    public static RmiResponse register(RmiRequest<User> rmiRequest) {
        if (Storage.getInstance().createUser(rmiRequest.getData())) {
            return new RmiResponse(SUCCESS, null);
        }
        return new RmiResponse(FAILED, null);
    }

    public static RmiResponse login(RmiRequest<User> rmiRequest) {
        if (Storage.getInstance().authenticateUser(rmiRequest.getData())) {
            loggedInUsers.add(rmiRequest.getData().getUsername());
            return new RmiResponse(SUCCESS, null);
        }
        return new RmiResponse(FAILED, null);
    }

    private static RmiResponse logout(RmiRequest<User> rmiRequest) {
        if(loggedInUsers.contains(rmiRequest.getUser().getUsername())) {
            loggedInUsers.remove(rmiRequest.getUser().getUsername());
        }
        return new RmiResponse(SUCCESS, null);
    }
}
