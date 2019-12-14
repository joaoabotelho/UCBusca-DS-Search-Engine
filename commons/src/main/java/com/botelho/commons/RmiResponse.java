package com.botelho.commons;

import java.io.Serializable;

public class RmiResponse<T extends Serializable> implements Serializable {
    private final ResponseStatus status;
    private final T data;

    public RmiResponse(final ResponseStatus status, final T data) {
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
