package com.solland.paidao.util;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationObjectSupport;

/**
 * @author by wb-sunshibo on 2015/4/22.
 */
public class ApplicationContextHolder extends WebApplicationObjectSupport {

    //声明静态变量保存
    private static WebApplicationContext webContext ;

    private static ServletContext servletContext ;

    /**
     * 获取WebApplicationContext
     * @return
     */
    public static WebApplicationContext getWebContext(){
        return webContext ;
    }

    /**
     * 获取ServletContext
     * @return
     */
    public static ServletContext getWebServletContext(){
        return servletContext ;
    }

    /**
     * 加载类的自动进行初始化的方法
     */
    @PostConstruct
    public void init(){
        webContext = getWebApplicationContext() ;
        servletContext = getServletContext() ;
    }

}
