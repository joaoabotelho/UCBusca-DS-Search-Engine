package com.botelho.commons.rmi;


import com.botelho.commons.Request;

import java.rmi.Remote;

public interface RmiServer extends Remote {
    public <T> T sendRequest(Request request) throws java.rmi.RemoteException;
}
