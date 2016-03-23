package com.solland.paidao.util.env;

import java.io.IOException;
import java.util.Properties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

/**
 * Created by sunshibo on 2016/3/22.
 */

@Service("env")
public class Env {

    /**
     * 自动扫描路径
     */
    public static final Resource[] DEFAULT_ENV_RESOURCES = ResourceUtil.getResources(new String[]
            {"classpath:conf/locale/*.properties"}, true);

    public String getProperty(String key) throws IOException {
        for (Resource resource : DEFAULT_ENV_RESOURCES) {
            if (resource.exists()) {
                Properties p = new Properties() ;
                p.load(resource.getInputStream());
                if (p.getProperty(key) != null ) {
                    return p.getProperty(key) ;
                }
            }
        }
        return null ;
    }

    public String getProperty(String key , String defaultValue) {
        String value = null ;
        try {
            value = this.getProperty(key) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  value == null ? defaultValue : value ;
    }

    public static void main(String[] args) {

        System.out.println(new Env().getProperty("0010007" , "0010007"));

    }
}
