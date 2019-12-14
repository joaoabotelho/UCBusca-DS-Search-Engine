package com.botelho.commons.action;

import com.botelho.commons.rmi.RmiClient;
import com.opensymphony.xwork2.ActionSupport;

import static com.botelho.commons.Configuration.RMI_PORT;
import static com.botelho.commons.Configuration.RMI_REGISTRY_NAME;

public class ActionSupportBase extends ActionSupport {
    private static final RmiClient rmiClient = RmiClient.getInstance(RMI_PORT, RMI_REGISTRY_NAME);

    protected static RmiClient getRmiClient() {
        return rmiClient;
    }
}
