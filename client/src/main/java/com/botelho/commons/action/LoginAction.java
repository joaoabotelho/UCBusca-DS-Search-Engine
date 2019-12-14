package com.botelho.commons.action;

import com.botelho.commons.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginAction extends ActionSupportBase {
    private User userBean;
    private static Logger logger = LoggerFactory.getLogger(LoginAction.class);

    public String execute() {

        //RMI call to login
        
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
