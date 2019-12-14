package com.botelho.commons.action;


import com.botelho.commons.RequestType;
import com.botelho.commons.RmiRequest;
import com.botelho.commons.RmiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchAction extends ActionSupportBase {
    private String search;
    private static Logger logger = LoggerFactory.getLogger(LoginAction.class);

    public String execute() {
        RmiResponse rmiResponse = getRmiClient().communicate(new RmiRequest<>(null, RequestType.SEARCH, search));
        logger.info("Search Action");
        return SUCCESS;
    }

    public String getSearch() { return search; }

    public void setSearch(final String search) { this.search = search; }
}
