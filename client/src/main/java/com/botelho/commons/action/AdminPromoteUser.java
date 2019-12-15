package com.botelho.commons.action;

import com.botelho.commons.RmiRequest;
import com.botelho.commons.RequestType;
import com.botelho.commons.RmiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Action extends ActionSupportBase {
    private User user;
    private static Logger logger = LoggerFactory.getLogger(LoginAction.class);

    public String execute() {
        RmiResponse rmiResponse = getRmiClient().communicate(new RmiRequest<>(null, RequestType.PROMOTEUSER, user));

        switch (rmiResponse.getStatus()) {
            case SUCCESS:
                logger.info("User {] promoted successfully.", user.getUsername());
                return SUCCESS;

            case FAILED:
                logger.info("Failed to to promote User {}", user.getUsername());
                return ERROR;
        }

        return null;
    }
}