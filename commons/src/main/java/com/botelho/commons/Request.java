package com.botelho.commons;

public class Request<T> {
    private final User user;
    private final RequestType type;
    private final T data;

    public Request(final User user, final RequestType type, T data) {
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
