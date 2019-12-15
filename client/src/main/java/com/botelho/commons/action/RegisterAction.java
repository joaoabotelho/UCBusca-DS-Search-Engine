package com.botelho.commons.action;

import com.botelho.commons.RequestType;
import com.botelho.commons.RmiRequest;
import com.botelho.commons.RmiResponse;
import com.botelho.commons.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterAction extends ActionSupportBase {
    private static Logger logger = LoggerFactory.getLogger(LoginAction.class);
    private User user;

    public String execute() {
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

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }
}
