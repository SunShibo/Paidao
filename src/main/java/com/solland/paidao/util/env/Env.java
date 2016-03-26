package com.solland.paidao.util.env;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

/**
 * Created by sunshibo on 2016/3/22.
 */

@Service("env")
public class Env {

    static final Logger log = LoggerFactory.getLogger(Env.class);
    /**
     * 自动扫描路径
     */
    public static final Resource[] DEFAULT_ENV_RESOURCES = ResourceUtil.getResources(new String[]
            {"classpath:conf/locale/*.properties"}, true);

    public String getProperty(String key){
        for (Resource resource : DEFAULT_ENV_RESOURCES) {
            if (resource.exists()) {
                Properties p = new Properties() ;
                try {
                    p.load(resource.getInputStream());
                } catch (IOException e) {
                    log.error("[Env - getProperty]  error:" + e);
                    return null ;
                }
                if (p.getProperty(key) != null ) {
                    return p.getProperty(key) ;
                }
            }
        }
        return null ;
    }

    public String getProperty(String key , String defaultValue) {
        String value = this.getProperty(key) ;
        return  value == null ? defaultValue : value ;
    }

    public static void main(String[] args) {

        System.out.println(new Env().getProperty("0010007" , "0010007"));

    }
}
