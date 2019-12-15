package com.botelho.commons.action;

import com.botelho.commons.RmiRequest;
import com.botelho.commons.RequestType;
import com.botelho.commons.RmiResponse;
import com.botelho.commons.User;
import com.botelho.commons.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginAction extends ActionSupportBase {
    private String username, password;
    private static Logger logger = LoggerFactory.getLogger(LoginAction.class);

    public String execute() {
        User user = new User(username, password, null);
        RmiResponse rmiResponse = getRmiClient().communicate(new RmiRequest<>(null, RequestType.LOGIN, user));

        switch (rmiResponse.getStatus()) {
            case SUCCESS:
                logger.info("User {} logged in.", user.getUsername());
                getSession().put(CURRENT_USER, user);
                if(user.getType() == UserType.ADMIN){
                    return "admin";
                }
                return SUCCESS;

            case FAILED:
                logger.info("User {} failed to login.", user.getUsername());
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
