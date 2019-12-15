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

    private static final List<User> loggedInUsers = new ArrayList<>();

    private Auth() {
    }

    public static RmiResponse register(RmiRequest<User> rmiRequest) {
        if (Storage.getInstance().createUser(rmiRequest.getData())) {
            return new RmiResponse(SUCCESS, null);
        }
        return new RmiResponse(FAILED, null);
    }

    public static RmiResponse login(RmiRequest<User> rmiRequest) {
        User user = Storage.getInstance().getUser(rmiRequest.getData());
        if (user != null) {
            loggedInUsers.add(user);
            return new RmiResponse(SUCCESS, user);
        }
        return new RmiResponse(FAILED, null);
    }

    private static RmiResponse logout(RmiRequest<User> rmiRequest) {
        loggedInUsers.remove(rmiRequest.getUser().getUsername());
        return new RmiResponse(SUCCESS, null);
    }
}
