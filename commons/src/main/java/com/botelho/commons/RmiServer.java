package com.botelho.commons;


import java.rmi.Remote;

public interface RmiServer extends Remote {
    public <T> T communicate(RmiRequest rmiRequest) throws java.rmi.RemoteException;
}
