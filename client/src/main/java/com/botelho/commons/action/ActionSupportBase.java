package com.botelho.commons.action;

import com.botelho.commons.Configuration;
import com.botelho.commons.rmi.RmiClient;
import com.opensymphony.xwork2.ActionSupport;

public class ActionSupportBase extends ActionSupport {
    private static final RmiClient rmiClient = RmiClient.getInstance(Configuration.RMI_PORT, Configuration.RMI_REGISTRY_NAME);

    protected static RmiClient getRmiClient() {
        return rmiClient;
    }
}
