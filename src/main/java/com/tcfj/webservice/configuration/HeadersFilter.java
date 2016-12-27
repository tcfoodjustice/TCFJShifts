package com.tcfj.webservice.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by jacob.headlee on 10/19/2016.
 */
@Configuration
public class HeadersFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(HeadersFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {}
}
