package com.botelho.rmi.server.manager.auth;

import com.botelho.commons.MulticastRequest;
import com.botelho.commons.MulticastResponse;
import com.botelho.commons.RmiRequest;
import com.botelho.commons.RmiResponse;
import com.botelho.rmi.server.RmiServerImpl;
import com.botelho.rmi.server.multicast.MulticastClient;
import com.botelho.rmi.server.utils.RequestUtils;
import com.botelho.rmi.server.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Auth {
    private static Logger logger = LoggerFactory.getLogger(Auth.class);

    private Auth() {
    }

    public static RmiResponse login(RmiRequest rmiRequest) {
        MulticastRequest multicastRequest = RequestUtils.rmiToMulticast(rmiRequest);
        logger.info("Logging in user");
        MulticastResponse response = MulticastClient.communicate(multicastRequest);
        return ResponseUtils.multicastToRmi(response);
    }

    private static void logout() {

    }
}
