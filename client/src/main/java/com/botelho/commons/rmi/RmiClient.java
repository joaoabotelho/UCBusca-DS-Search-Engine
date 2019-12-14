package com.botelho.commons.rmi;

import com.botelho.commons.Request;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RmiClient {
    private static RmiClient instance;
    private static RmiServer rmiServer;

    private RmiClient(int port, String registryName) {
        try {
            rmiServer = (RmiServer) LocateRegistry.getRegistry(port).lookup(registryName);
        } catch (RemoteException | NotBoundException e) {
            throw new RuntimeException("Couldn't get a remote reference to the rmi server.");
        }
    }

    public static RmiClient getInstance(int port, String registryName) {
        if(instance == null){
            synchronized (RmiClient.class) {
                if(instance == null){
                    instance = new RmiClient(port, registryName);
                }
            }
        }
        return instance;
    }

    public void send(Request request) {

    }
}
