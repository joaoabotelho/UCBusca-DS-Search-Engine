package com.botelho.commons;

import java.io.Serializable;

public class RmiRequest<T> implements Serializable {
    private final User user;
    private final RequestType type;
    private final T data;

    public RmiRequest(final User user, final RequestType type, T data) {
        this.user = user;
        this.type = type;
        this.data = data;
    }

    public User getUser() {
        return user;
    }

    public RequestType getType() {
        return type;
    }

    public T getData() {
        return data;
    }
}
