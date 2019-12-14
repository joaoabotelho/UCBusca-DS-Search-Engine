package com.botelho.commons;

public class Response<T> {
    private final ResponseStatus status;
    private final T data;

    public Response(final ResponseStatus status, final T data) {
        this.status = status;
        this.data = data;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }
}
