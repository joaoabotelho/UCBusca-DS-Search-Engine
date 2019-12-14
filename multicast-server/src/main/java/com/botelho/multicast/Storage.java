package com.botelho.multicast;

import com.botelho.commons.User;

import java.util.HashMap;
import java.util.List;

public class Storage {
    private final HashMap<String, User> users = new HashMap<>();

    private Storage() {
    }

    public static Storage getInstance() {
        return SingletonHelper.INSTANCE;
    }

    private static class SingletonHelper {
        private static final Storage INSTANCE = new Storage();
    }

    public boolean authenticateUser(User user) {
        if(users.containsKey(user.getUsername())) {
            return users.get(user.getUsername()).getPassword().equals(user.getPassword());
        }
        return false;
    }
}
