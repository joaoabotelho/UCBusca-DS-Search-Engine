package com.botelho.commons.action;

import com.botelho.commons.RmiRequest;
import com.botelho.commons.RequestType;
import com.botelho.commons.RmiResponse;
import com.botelho.commons.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminPromoteUserAction extends ActionSupportBase {
    private String username;
    private static Logger logger = LoggerFactory.getLogger(AdminPromoteUserAction.class);

    public String execute() {
        RmiResponse rmiResponse = getRmiClient().communicate(new RmiRequest<>(null, RequestType.PROMOTEUSER, username));

        switch (rmiResponse.getStatus()) {
            case SUCCESS:
                logger.info("User {] promoted successfully.",  username);
                return SUCCESS;

            case FAILED:
                logger.info("Failed to to promote User {}",  username);
                return ERROR;
        }

        return null;
    }

    public String getUsername() {return  username;}

    public void setUsername(final String username) { this. username = username;}
}