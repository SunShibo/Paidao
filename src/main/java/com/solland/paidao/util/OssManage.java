package com.solland.paidao.util;

import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.AppendObjectRequest;
import com.aliyun.oss.model.AppendObjectResult;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

/**
 * Created by sunshibo on 2016/3/17.
 */
@Service("ossManage")
public class OssManage {
    public static final String ACCESS_KEY_ID  = "2NcaNTwp5kZxxQ83";
    public static final String  ACCESS_KEY_SECRET = "VU2kscTuqh23vZaWnwcnC9Vpojf4s8";
    public static final String  ENDPOINT = "https://oss-cn-beijing.aliyuncs.com";
    public static final String  BUCKETNAME = "paidao";

    /**
     * @Description: 上传文件到OSS文件服务器
     * @param content  文件流
     * @param key    上传为OSS文件服务器的唯一标识
     * @param mimeType  文档类型
     * @throws Exception
     * @ReturnType:String   图片的url
     */
    public String uploadFile(InputStream content,String key,String mimeType){

        OSSClient client  = new OSSClient(ENDPOINT,ACCESS_KEY_ID, ACCESS_KEY_SECRET) ;
        ObjectMetadata meta = new ObjectMetadata();
        // 必须设置ContentLength
        meta.setContentType(mimeType);
        // 上传Object.
        String url = "http://"+BUCKETNAME+".oss-cn-beijing.aliyuncs.com/" +  key + "." + mimeType;
        client.putObject(BUCKETNAME, key + "." + mimeType , content, meta);
        client.shutdown();
        return url;

    }



    /**
     * @Description: 根据key获取oss服务器上的图片地址
     * @param key
     * @return
     * @ReturnType:String
     */
//    public String getImgURl(String key){
//        init();
//        Date expires = new Date (new Date().getTime() + Integer.parseInt(OUT_OF_DATE)); // 30 minute to expire
//        GeneratePresignedUrlRequest generatePresignedUrlRequest ;
//        if(key.startsWith("msplatform")){
//            generatePresignedUrlRequest =new GeneratePresignedUrlRequest(BUCKETNAME, key);
//        }else {
//            generatePresignedUrlRequest =new GeneratePresignedUrlRequest(BUCKETNAME_VIDEO, key);
//        }
//        generatePresignedUrlRequest.setExpiration(expires);
//        URL url = client.generatePresignedUrl(generatePresignedUrlRequest);
//        return url.toString();
//    }
    /**
     * @Description:根据key获取oss服务器上的ipa文件地址
     * @param key
     * @return
     * @ReturnType:String

    public String getIpaURl(String key){
        init();
        Date expires = new Date(new Date().getTime()+ 10*365*24*3600*1000);
        GeneratePresignedUrlRequest generatePresignedUrlRequest ;
        if(key.startsWith("msplatform")){
            generatePresignedUrlRequest =new GeneratePresignedUrlRequest(BUCKETNAME, key);
        }else {
            generatePresignedUrlRequest =new GeneratePresignedUrlRequest(BUCKETNAME_VIDEO, key);
        }
        generatePresignedUrlRequest.setExpiration(expires);
        URL url = client.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    } */

    /**
     * @Description: 根据key获取oss服务器上的图片地址
     * @param key
     * @return
     * @ReturnType:String

    public InputStream  getObject(String key){
        init();
        OSSObject object = null;
        if(key.startsWith("msplatform")){
            object= client.getObject(BUCKETNAME, key);
        }else{
            object= client.getObject(BUCKETNAME_VIDEO, key);
        }
        object.getObjectMetadata().getContentType();
        // 获取Object的输入流
        InputStream objectContent = object.getObjectContent();


        return objectContent;
    }
     */

    /**
     * @Description:删除文件
     * @param key  OSS文件服务器上文件的唯一标识
     * @ReturnType:void
     */
    public void deleteFile(String key){
        OSSClient ossClient = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        ossClient.deleteObject(BUCKETNAME, key);
        ossClient.shutdown();
    }


    /**
     * @Description: 断点上传文件到OSS文件服务器
     * @param content  文件流
     * @param key    上传为OSS文件服务器的唯一标识
     * @param position 位置
     */
    public String  appendObjectFile(InputStream content,String key,int position,String mimeType) throws Exception{
        //进行初始化
        OSSClient client  = new OSSClient(ENDPOINT,ACCESS_KEY_ID, ACCESS_KEY_SECRET) ;
        ObjectMetadata meta = new ObjectMetadata();
        // 必须设置ContentLength
        meta.setContentLength(position);
        meta.setContentType(mimeType);
        meta.setCacheControl("no-cache");
        meta.setContentEncoding("utf-8");
        // 上传
        System.out.println("*****************断点上传图片到oss服务器开始*****************" + key);
        AppendObjectRequest appendObjectRequest = new AppendObjectRequest(BUCKETNAME, key, content, meta);
        appendObjectRequest.setPosition(Long.valueOf(position));
        AppendObjectResult appendObjectResult =client.appendObject(appendObjectRequest);
        System.out.println("*****************断点上传图片到oss服务器结束*****************" + key);
        client.shutdown();
        return appendObjectResult.getNextPosition().toString();
    }

    public static void main(String[] args) {
        OssManage ossManage = new OssManage();
    }
}
