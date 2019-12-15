package com.botelho.commons.action;

import com.botelho.commons.RmiRequest;
import com.botelho.commons.RmiResponse;
import com.botelho.commons.Search;
import com.botelho.commons.ResponseStatus;
import com.botelho.commons.RequestType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;

public class PreviousSearchAction extends ActionSupportBase {
    private static Logger logger = LoggerFactory.getLogger(LoginAction.class);
    private ArrayList<Search> result;

    public String execute() {
        RmiResponse<ArrayList<Search>> rmiResponse = getRmiClient().communicate(new RmiRequest<>(null, RequestType.PREVIOUSSEARCH, currentUser()));

        if (rmiResponse.getStatus() == ResponseStatus.SUCCESS) {
            logger.info("Previous Searches completed.");
            result = rmiResponse.getData();
            logger.info(Arrays.toString(rmiResponse.getData().toArray()));
            return SUCCESS;
        }
        return ERROR;
    }


    public void setResult(ArrayList<Search> result) {
        this.result = result;
    }

    public ArrayList<Search> getResult() {
        return result;
    }
}
