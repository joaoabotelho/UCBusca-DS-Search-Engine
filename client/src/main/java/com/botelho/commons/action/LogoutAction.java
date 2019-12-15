package com.botelho.commons.action;

import com.botelho.commons.RequestType;
import com.botelho.commons.RmiRequest;
import com.botelho.commons.RmiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LogoutAction extends ActionSupportBase {
    private static Logger logger = LoggerFactory.getLogger(LoginAction.class);

    public String execute() {
        RmiResponse rmiResponse = getRmiClient().communicate(new RmiRequest<>(null, RequestType.LOGIN, currentUser()));

        switch (rmiResponse.getStatus()) {
            case SUCCESS:
                logger.info("User logged out.");
                getSession().remove(CURRENT_USER);
                return SUCCESS;

            case FAILED:
                logger.info("User failed to logout.");
                return ERROR;
        }
        return SUCCESS;
    }
}
