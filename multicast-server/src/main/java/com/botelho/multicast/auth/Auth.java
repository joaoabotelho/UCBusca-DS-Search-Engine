package com.botelho.multicast.auth;

import com.botelho.commons.MulticastRequest;
import com.botelho.commons.MulticastResponse;
import com.botelho.commons.ResponseStatus;
import com.botelho.commons.User;
import com.botelho.multicast.Storage;

import java.util.ArrayList;
import java.util.List;

import static com.botelho.commons.ResponseStatus.FAILED;
import static com.botelho.commons.ResponseStatus.SUCCESS;

public class Auth {
    private final List<User> loggedInUsers = new ArrayList<>();

    private Auth() {
    }

    public static Auth getInstance() {
        return Auth.SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        private static final Auth INSTANCE = new Auth();
    }

    public MulticastResponse login(MulticastRequest<User> request) {
        if(Storage.getInstance().authenticateUser(request.getData())) {
            return new MulticastResponse(request.getIdentifier(), SUCCESS, null);
        }
        return new MulticastResponse(request.getIdentifier(), FAILED, null);
    }
}
