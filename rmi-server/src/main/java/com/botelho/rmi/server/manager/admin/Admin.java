package com.botelho.rmi.server.manager.admin;

import com.botelho.commons.RmiRequest;
import com.botelho.commons.RmiResponse;
import com.botelho.commons.User;
import com.botelho.rmi.server.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.botelho.commons.ResponseStatus.FAILED;
import static com.botelho.commons.ResponseStatus.SUCCESS;

public class Admin {
    private static Logger logger = LoggerFactory.getLogger(Admin.class);

    private Admin() {
    }

    public static RmiResponse promoteUser(RmiRequest<String> rmiRequest) {
        if (Storage.getInstance().promoteUser(rmiRequest.getData())) {
            return new RmiResponse(SUCCESS, null);
        }
        return new RmiResponse(FAILED, null);
    }

    public static RmiResponse createUrl(RmiRequest<String> rmiRequest) {
        try {
            if (Storage.getInstance().newUrlToIndex(rmiRequest.getData(), 0)) {
                return new RmiResponse(SUCCESS, null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new RmiResponse(FAILED, null);
    }
}