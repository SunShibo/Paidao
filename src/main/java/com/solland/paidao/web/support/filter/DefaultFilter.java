package com.solland.paidao.web.support.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.solland.paidao.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Created by sunshibo on 2016/4/11.
 */
public class DefaultFilter extends OncePerRequestFilter {

    static final Logger log = LoggerFactory.getLogger(DefaultFilter.class);

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        log.debug("URl:{} \t content type:{}  ", request.getRequestURI(), request.getContentType());
        log.debug("param:{}  ", JsonUtils.getJsonString4JavaPOJO(request.getParameterMap()));
        //到过滤器链的下一个过滤器
        filterChain.doFilter(request, response);
    }
}