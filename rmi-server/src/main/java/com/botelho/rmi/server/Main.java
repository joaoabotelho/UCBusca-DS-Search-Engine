package com.botelho.rmi.server;

import com.botelho.commons.RmiServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import static com.botelho.commons.Configuration.RMI_PORT;
import static com.botelho.commons.Configuration.RMI_REGISTRY_NAME;

public class Main {
    private static Logger logger = LoggerFactory.getLogger(RmiServerImpl.class);

    public static void main(String[] args) throws RemoteException {
        RmiServer rmiServer = new RmiServerImpl();

        try {
            FileInputStream fileIn = new FileInputStream(new File("./storage"));
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Storage.setInstance((Storage) objectIn.readObject());
        } catch (IOException | ClassNotFoundException e) {
            logger.info("Didn't load from file.");
        }

        RmiServer stub = (RmiServer) UnicastRemoteObject
                .exportObject(rmiServer, 0);
        LocateRegistry.createRegistry(RMI_PORT).rebind(RMI_REGISTRY_NAME, stub);
        logger.info("RMI Server successfully started.");
        new Thread(() -> Storage.getInstance().seedStorage()).start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Storage.getInstance().close();
        }));
    }
}
