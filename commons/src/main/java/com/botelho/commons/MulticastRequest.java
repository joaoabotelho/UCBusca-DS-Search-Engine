package com.botelho.commons;

import java.io.Serializable;
import java.util.UUID;

import static com.botelho.commons.ServerType.TO_MULTICAST;

public class MulticastRequest<T> implements Serializable {
    private final UUID identifier = UUID.randomUUID();
    private final RequestType actionType;
    private final ServerType serverType = TO_MULTICAST;
    private final T data;

    public MulticastRequest(RequestType actionType, T data) {
        this.actionType = actionType;
        this.data = data;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public T getData() {
        return data;
    }

    public RequestType getActionType() {
        return actionType;
    }
}
