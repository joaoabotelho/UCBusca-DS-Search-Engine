package com.botelho.commons.rmi;

import com.botelho.commons.RmiRequest;
import com.botelho.commons.RmiResponse;
import com.botelho.commons.RmiServer;
import com.botelho.commons.action.LoginAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RmiClient {
    private static RmiClient instance;
    private static RmiServer rmiServer;
    private static Logger logger = LoggerFactory.getLogger(LoginAction.class);

    private RmiClient(int port, String registryName) {
        try {
            rmiServer = (RmiServer) LocateRegistry.getRegistry(port).lookup(registryName);
        } catch (RemoteException | NotBoundException e) {
            throw new RuntimeException("Couldn't get a remote reference to the rmi server.");
        }
        logger.info("Connected to RMI Server.");
    }

    public static RmiClient getInstance(int port, String registryName) {
        if (instance == null) {
            synchronized(RmiClient.class) {
                if (instance == null) {
                    instance = new RmiClient(port, registryName);
                }
            }
        }
        return instance;
    }

    public RmiResponse communicate(RmiRequest rmiRequest) {
        try {
            return rmiServer.communicate(rmiRequest);
        } catch (RemoteException e) {
            throw new RuntimeException("Couldn't communicate with RMI Server", e);
        }
    }
}
