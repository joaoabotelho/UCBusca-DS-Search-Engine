package com.botelho.commons.action;

import com.botelho.commons.RmiRequest;
import com.botelho.commons.RequestType;
import com.botelho.commons.RmiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminCreateUrlAction extends ActionSupportBase {
    private String url;
    private static Logger logger = LoggerFactory.getLogger(LoginAction.class);

    public String execute() {
        RmiResponse rmiResponse = getRmiClient().communicate(new RmiRequest<>(null, RequestType.CREATEURL, url));

        switch (rmiResponse.getStatus()) {
            case SUCCESS:
                logger.info("Created url successfully.", url);
                return SUCCESS;

            case FAILED:
                logger.info("Failed to index url", url);
                return ERROR;
        }

        return null;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
