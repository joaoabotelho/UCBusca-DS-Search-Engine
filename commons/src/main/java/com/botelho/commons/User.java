package com.botelho.commons;

import java.io.Serializable;

public class User implements Serializable {
    private String username;

    private String password;

    private UserType type;

    public User(final UserType type){ this.type = type; }

    public User(final String username, final UserType type) {
        this(username, null, type);
    }

    public User(final String username, final String password, final UserType type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public UserType getType() {
        return type;
    }
}
