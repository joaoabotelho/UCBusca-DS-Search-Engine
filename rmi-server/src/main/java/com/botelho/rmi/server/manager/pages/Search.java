package com.botelho.rmi.server.manager.pages;

import com.botelho.commons.*;
import com.botelho.rmi.server.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Search {
    private static Logger logger = LoggerFactory.getLogger(Search.class);



    public static RmiResponse searchByWords(RmiRequest<String> rmiRequest){
        logger.info("Splitting words to List");
        List<String> data = Arrays.asList(rmiRequest.getData().split(" "));

        logger.info("Searching in storage");
        ArrayList<WebPage> result = Storage.getInstance().searchFor(data, rmiRequest.getUser());
        logger.info("Finished in storage");

        return new RmiResponse(ResponseStatus.SUCCESS, result);
    }
}
