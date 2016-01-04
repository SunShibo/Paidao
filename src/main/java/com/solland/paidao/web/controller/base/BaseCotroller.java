package com.solland.paidao.web.controller.base;

import com.google.common.collect.Lists;
import com.solland.paidao.util.RedisUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * @author Shibo Sun
 */
public class BaseCotroller {

    /**
     * 异步返回结果
     *
     * @param response
     * @param str
     */
    public void safeJsonPrint(HttpServletResponse response, String str) {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = null;
        try {
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.write(str);
        } finally {
            if (out != null)
                out.close();
        }
    }

    /**
     * 异步返回结果
     *
     * @param response
     * @param str
     */
    public void safeTextPrint(HttpServletResponse response, String str) {
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = null;
        try {
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.write(str);
        } finally {
            if (out != null)
                out.close();
        }
    }

    /**
     * 此方法防止当缓冲区满之后将无法继续写入数据，最终造成阻塞在waitFor()这里
     * @param process
     * @return
     */
    public int safeRun(Process process){
        try {
            // 保存进程的输入流信息
            List<String> stdoutList = Lists.newArrayList();
            // 保存进程的错误流信息
            List<String> erroroutList = Lists.newArrayList();

            // 先清空
            stdoutList.clear();
            erroroutList.clear();

            // 创建2个线程，分别读取输入流缓冲区和错误流缓冲区
            ThreadUtil stdoutUtil = new ThreadUtil(process.getInputStream(), stdoutList);
            ThreadUtil erroroutUtil = new ThreadUtil(process.getErrorStream(), erroroutList);
            //启动线程读取缓冲区数据
            stdoutUtil.start();
            erroroutUtil.start();

            return process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    class ThreadUtil implements Runnable {
        // 设置读取的字符编码
        private String character = "UTF-8";
        private List<String> list;
        private InputStream inputStream;

        public ThreadUtil(InputStream inputStream, List<String> list) {
            this.inputStream = inputStream;
            this.list = list;
        }

        public void start() {
            Thread thread = new Thread(this);
            thread.setDaemon(true);//将其设置为守护线程
            thread.start();
        }

        public void run() {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(inputStream, character));
                String line = null;
                while ((line = br.readLine()) != null) {
                    if (line != null) {
                        if(!br.ready()){
                            break;
                        }
                        list.add(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    //释放资源
                    inputStream.close();
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *获取各种类型表单的表单参数
     *@paramrequest HttpServletRequest请求对像
     * @paramparamName 参数名
     *@return
     *@throwsFileUploadException
     */
    public static String getParameterValue(HttpServletRequest request,String paramName) {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if(isMultipart==true){
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List fileItemList = null;
            try {
                fileItemList = upload.parseRequest(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            if(fileItemList!=null){
                for(Iterator itr=fileItemList.iterator();itr.hasNext();){
                    FileItem fileItem = (FileItem)itr.next();
                    if(fileItem.getFieldName().equalsIgnoreCase(paramName)){
                        try {
                            return new String(fileItem.getString().getBytes("ISO8859-1"));//中文转码
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }else{
            try {
                return new String(request.getParameter(paramName).getBytes("ISO8859-1"));//中文转码
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return"";
    }

    /**session赋值*/
    public void putSession (String key , Object value) {
        RedisUtil.set(value , key) ;
    }

    /**获取session*/
    public Object getSession (String key) {
        return RedisUtil.get(key) ;
    }


    /**移除session*/
    public void removeSession (String key) {
        RedisUtil.del(key) ;
    }
}
