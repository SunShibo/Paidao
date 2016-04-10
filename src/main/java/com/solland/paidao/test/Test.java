package com.solland.paidao.test;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by sunshibo on 2016/3/23.
 */
public class Test {
    /**
     * 执行一个HTTP GET请求，返回请求响应的HTML
     *
     * @param url                 请求的URL地址
     * @param queryString 请求的查询参数,可以为null
     * @return 返回请求响应的HTML
     */
    public static String doGet(String url, String queryString) {
        String response = null;
        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(url);
        try {
            if (StringUtils.isNotBlank(queryString))
                method.setQueryString(URIUtil.encodeQuery(queryString));
            client.executeMethod(method);
            if (method.getStatusCode() == HttpStatus.SC_OK) {
                response = method.getResponseBodyAsString();
            }
        } catch (URIException e) {
            System.out.println("执行HTTP Get请求时，编码查询字符串“" + queryString + "”发生异常！");
        } catch (IOException e) {
            System.out.println("执行HTTP Get请求" + url + "时，发生异常！");
        } finally {
            method.releaseConnection();
        }
        return response;
    }

    /**
     * 执行一个HTTP POST请求，返回请求响应的HTML
     *
     * @param url        请求的URL地址
     * @param params 请求的查询参数,可以为null
     * @return 返回请求响应的HTML
     */
    public static String doPost(String url, Map<String, String> params) {
        String response = null;
        HttpClient client = new HttpClient();
        HttpMethod method = new PostMethod(url);
        for (Iterator it = params.entrySet().iterator(); it.hasNext();) {

        }
        //设置Http Post数据
        if (params != null) {
            HttpMethodParams p = new HttpMethodParams();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                p.setParameter(entry.getKey(), entry.getValue());
            }
            method.setParams(p);
        }
        try {
            client.executeMethod(method);
            if (method.getStatusCode() == HttpStatus.SC_OK) {
                response = method.getResponseBodyAsString();
            }
        } catch (IOException e) {
            System.out.println("执行HTTP Post请求" + url + "时，发生异常！");
        } finally {
            method.releaseConnection();
    }

        return response;
    }

    public static void main(String[] args) {
        Map hashMap = new HashMap();
        hashMap.put("accunt" , "395831708@qq.com") ;
        hashMap.put("password" , "123456") ;
        String x = doPost("http://127.0.0.1:8080/login/signIn", hashMap);
        System.out.println(x);
    }


}
