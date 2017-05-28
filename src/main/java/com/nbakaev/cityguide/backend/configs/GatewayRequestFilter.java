/*
 * Copyright (c) 2016. com.biqasoft
 */

package com.nbakaev.cityguide.backend.configs;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GatewayRequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        response.setHeader("Access-Control-Max-Age", "3600");
        response.addHeader("Access-Control-Allow-Credentials", "true");

        // CORS, allow all use our API via Ajax; this is secure because frontend manually set basic auth header
        response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));

        if (request.getMethod().equals("OPTIONS")) {
            response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
            response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, Date");
            response.setHeader("X-Frame-Options", "DENY"); //SAMEORIGIN

            // if http request is options - do not process filters chain after
            // because we have user authentication filter and it will fail
            // with exception
            return;
        }

        chain.doFilter(req, res);
    }

    public void destroy() {
    }

}