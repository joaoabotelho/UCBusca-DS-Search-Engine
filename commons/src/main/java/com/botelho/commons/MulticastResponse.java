package com.botelho.commons;

import java.io.Serializable;
import java.util.UUID;

public class MulticastResponse<T> implements Serializable {
    private final UUID identifier;
    private final ResponseStatus status;
    private final ServerType serverType = ServerType.TO_RMI;
    private final T data;

    public MulticastResponse(final UUID identifier, ResponseStatus status, final T data) {
        this.identifier = identifier;
        this.status = status;
        this.data = data;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public T getData() {
        return data;
    }

    public ResponseStatus getStatus() {
        return status;
    }
}
