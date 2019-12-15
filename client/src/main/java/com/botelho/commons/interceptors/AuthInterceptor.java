package com.botelho.commons.interceptors;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import java.util.Map;

import static com.botelho.commons.action.ActionSupportBase.CURRENT_USER;

public class AuthInterceptor implements Interceptor {
    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    @Override
    public String intercept(final ActionInvocation actionInvocation) throws Exception {
        Map<String, Object> session = actionInvocation.getInvocationContext().getSession();

        if (session.containsKey(CURRENT_USER)) {
            return actionInvocation.invoke();
        } else {
            return "auth_failed";
        }
    }
}
