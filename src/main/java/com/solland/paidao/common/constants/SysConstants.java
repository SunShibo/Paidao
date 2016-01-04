package com.solland.paidao.common.constants;

/**
 * Created by sunshibo on 2016/1/4.
 */
public class SysConstants {
    /**所有权限*/
    public static final String ALL_PERMISSION = "all_permission";

    /**
     * tair命名空间
     */
    int TAIR_NAME_SPACE = 851;

    /**
     * OSS CONFIG
     */

    //静态资源文件夹
    public static final String OSS_FOLDER_STATIC = "static/";
    //JS文件夹
    public static final String OSS_FOLDER_STATIC_JS = "static/js/";
    //静态样式文件夹
    public static final String OSS_FOLDER_STATIC_CSS = "static/css/";
    //静态图片文件夹
    public static final String OSS_FOLDER_STATIC_IMG = "static/img/";
    //业务（屏保、壁纸）资源文件夹
    public static final String OSS_FOLDER_RES = "res/";
    //屏保资源文件夹
    public static final String OSS_FOLDER_RES_SAVER = "res/saver/";
    //壁纸资源文件夹
    public static final String OSS_FOLDER_RES_WALLPAPER = "res/wallpaper/";




    /**
     * 当前用户
     */
    public static final String CURRENT_USER = "user";

    public static final String MENU = "menu";

    public static final String BUTTON = "button";

    /**所有的权限资源map*/
    public static final String ALL_RESOURCE_MAP = "all_resource_map" ;

    /**用户的权限资源map*/
    public static final String USERS_RESOURCE_MAP = "users_resource_map" ;

    /**
     * TAOBAO账号系统全局常量
     */
    public static final String LOGIN_APPNAME = "alidm";
    public static final String	LOGIN_TAOBAO_TICKET = "login_taobao_ticket";
    public static final String	LOGIN_TAOBAO_NAME = "login_taobao_name";

    public static final String	DM_SESSION_ID = "dm_session_id";
//    public static final String	APP_KEY = "ab39e90fa86e5f3f089b1cb9b7a50386";
//    public static final String	APP_SEC	= "885795f6cf2f5a1bc4c83434e2b1acc9";
//    public static final String  LOGIN_API_URL = "https://account.yunos.com/openapi";
//    public static final Integer MAX_PAGE = 100;


    /**
     * 合法的IMEI号的长度
     */
    public static int VALID_IMEI_LENGTH = 15;
    /**
     * 合法的MEID号的长度
     */
    public static int VALID_MEID_LENGTH = 14;
    /**
     * 合法的UUID的长度
     */
    public static int VALID_UUID_LENGTH = 32;

    /**
     *  文件上传进度的前缀
     *  用于放在tair中做前缀名
     */
    public static final String FILE_UPLOAD_PROGRESSED_ = "SUP_" ;



    /**
     *  上传进度
     */
    public static final String UPLOAD_PROGRESSED = "upload_progressed" ;
}
