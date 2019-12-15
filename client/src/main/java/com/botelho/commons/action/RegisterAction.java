package com.botelho.commons.action;

import com.botelho.commons.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterAction extends ActionSupportBase {
    private static Logger logger = LoggerFactory.getLogger(LoginAction.class);
    private String username, password;

    public String execute() {
        User user = new User(username, password, UserType.NORMAL);
        RmiResponse rmiResponse = getRmiClient().communicate(new RmiRequest<>(null, RequestType.REGISTER, user));

        switch (rmiResponse.getStatus()) {
            case SUCCESS:
                logger.info("User {} registered.", user.getUsername());
                return SUCCESS;

            case FAILED:
                logger.info("User {} failed to register.", user.getUsername());
                return ERROR;
        }

        return null;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
