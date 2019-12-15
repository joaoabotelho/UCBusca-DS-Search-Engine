package com.botelho.rmi.server;

import com.botelho.commons.RmiServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import static com.botelho.commons.Configuration.RMI_PORT;
import static com.botelho.commons.Configuration.RMI_REGISTRY_NAME;

public class Main {
    private static Logger logger = LoggerFactory.getLogger(RmiServerImpl.class);

    public static void main(String[] args) throws RemoteException {
        RmiServer rmiServer = new RmiServerImpl();

        RmiServer stub = (RmiServer) UnicastRemoteObject
                .exportObject(rmiServer, 0);
        LocateRegistry.createRegistry(RMI_PORT).rebind(RMI_REGISTRY_NAME, stub);
        logger.info("RMI Server successfully started.");
        new Thread(() -> Storage.getInstance()).start();
    }
}
