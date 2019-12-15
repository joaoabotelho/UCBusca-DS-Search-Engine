package com.botelho.commons.action;


import com.botelho.commons.RequestType;
import com.botelho.commons.RmiRequest;
import com.botelho.commons.RmiResponse;
import com.botelho.commons.WebPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class SearchAction extends ActionSupportBase {
    private String search;
    private static Logger logger = LoggerFactory.getLogger(LoginAction.class);
    private ArrayList<WebPage> result;


    public String execute() {
        logger.info("Start search for {}", search);
        RmiResponse<ArrayList<WebPage>> rmiResponse = getRmiClient().communicate(new RmiRequest<>(null, RequestType.SEARCH, search));

        switch(rmiResponse.getStatus()) {
            case SUCCESS:
                logger.info("Search completed.");
                result = rmiResponse.getData();
                logger.info(Arrays.toString(rmiResponse.getData().toArray()));
                return SUCCESS;
            case FAILED:
                logger.info("Search not permitted");
                return ERROR;
        }
        return null;
    }

    public String getSearch() { return search; }

    public ArrayList<WebPage> getResult() { return result; }

    public void setSearch(final String search) { this.search = search; }

    public void setResult(final ArrayList<WebPage> result) { this.result = result; }
}
