package com.botelho.rmi.server.manager.auth;

import com.botelho.commons.RmiRequest;
import com.botelho.commons.RmiResponse;
import com.botelho.commons.User;
import com.botelho.rmi.server.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.botelho.commons.ResponseStatus.FAILED;
import static com.botelho.commons.ResponseStatus.SUCCESS;

public class Auth {
    private static Logger logger = LoggerFactory.getLogger(Auth.class);

    private Auth() {
    }

    public static RmiResponse login(RmiRequest<User> rmiRequest) {
        if(Storage.getInstance().authenticateUser(rmiRequest.getData())) {
            return new RmiResponse(SUCCESS, null);
        }
        return new RmiResponse(FAILED, null);
    }

    private static void logout() {

    }
}
