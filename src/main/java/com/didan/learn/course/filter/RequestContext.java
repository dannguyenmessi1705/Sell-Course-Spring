package com.didan.learn.course.filter;

import jakarta.servlet.http.HttpServletRequest;

public class RequestContext {
    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<>();

    public static void setRequest(HttpServletRequest request) {
        requestHolder.set(request);
    }
    
    public static HttpServletRequest getRequest() {
        return requestHolder.get();
    }

    public static void removeRequest() {
        requestHolder.remove();
    }
}
