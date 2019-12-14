package com.botelho.commons.action;

import com.botelho.commons.RmiRequest;
import com.botelho.commons.RequestType;
import com.botelho.commons.RmiResponse;
import com.botelho.commons.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginAction extends ActionSupportBase {
    private User userBean;
    private static Logger logger = LoggerFactory.getLogger(LoginAction.class);

    public String execute() {
        RmiResponse rmiResponse = getRmiClient().communicate(new RmiRequest<>(null, RequestType.LOGIN, userBean));

        switch (rmiResponse.getStatus()) {
            case SUCCESS:
                logger.info("User {} logged in.", userBean.getUsername());
                break;

            case FAILED:
                logger.info("User {} failed to login.", userBean.getUsername());
                break;
        }

        logger.info("User {} logged in.", userBean.getUsername());

        return SUCCESS;
    }

    public User getUserBean() {
        return userBean;
    }

    public void setUserBean(final User userBean) {
        this.userBean = userBean;
    }
}
