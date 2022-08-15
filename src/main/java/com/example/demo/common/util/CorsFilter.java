package com.example.demo.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class CorsFilter implements Filter {

    static List<String> allowOriginList = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info(">>> CorsFilter init");
        allowOriginList.add("http://192.168.35.157:50884");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        var method = request.getMethod();
        var origin = request.getHeader("origin");

        if (method.equals("OPTIONS") && origin != null && !allowOriginList.contains(origin)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        log.info(">>> CorsFilter Do");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        chain.doFilter(req, response);
    }
}