package com.botelho.commons.action;

import com.botelho.commons.User;
import com.botelho.commons.UserType;
import com.botelho.commons.rmi.RmiClient;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

import static com.botelho.commons.Configuration.RMI_PORT;
import static com.botelho.commons.Configuration.RMI_REGISTRY_NAME;

public abstract class ActionSupportBase extends ActionSupport implements SessionAware {
    public static final String CURRENT_USER = "currentUser";

    private final RmiClient rmiClient = RmiClient.getInstance(RMI_PORT, RMI_REGISTRY_NAME);

    private Map<String, Object> session;

    protected RmiClient getRmiClient() {
        return rmiClient;
    }

    protected User currentUser() {
        if(session.get(CURRENT_USER) != null) {
            return (User) session.get(CURRENT_USER);
        }
        return new User(UserType.ANONYMOUS);
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public Map<String, Object> getSession() {
        return session;
    }
}
