package com.botelho.rmi.server;

import com.botelho.commons.RmiRequest;
import com.botelho.commons.RmiResponse;
import com.botelho.commons.RmiServer;
import com.botelho.rmi.server.routing.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.RemoteException;

public class RmiServerImpl implements RmiServer {
    private static Logger logger = LoggerFactory.getLogger(RmiServerImpl.class);

    @Override
    public RmiResponse communicate(final RmiRequest rmiRequest) throws RemoteException {
        logger.info("Received {} request.", rmiRequest.getType());
        return Router.route(rmiRequest);
    }
}
