package com.botelho.rmi.server;

import com.botelho.commons.User;

import java.util.HashMap;

public class Storage {
    private final HashMap<String, User> users = new HashMap<>();

    private Storage() {
    }

    public static Storage getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public boolean authenticateUser(User user) {
        System.out.println(user.getUsername());
        if (users.containsKey(user.getUsername())) {
            return users.get(user.getUsername()).getPassword().equals(user.getPassword());
        }
        return false;
    }

    public boolean createUser(User user) {
        if(!users.containsKey(user.getUsername())) {
            users.put(user.getUsername(), user);
            return true;
        }
        return false;
    }

    private static class SingletonHelper {
        private static final Storage INSTANCE = new Storage();
    }


}
