package com.example.webdemo;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class HandlerExample implements HandlerInterceptor {
    private static int counter = 0;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if( request.getMethod().equals(HttpMethod.POST.toString()) ) {
            HandlerExample.counter += HandlerExample.counter;
        }
        System.out.println("counter : " + HandlerExample.counter);
        return false;
    }
}
