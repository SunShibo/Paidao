package com.solland.paidao.util;

import java.util.Random;

/**
 * 序列号生成器
 * Created by shibo on 2015/8/28.
 */
public class SerialNumberTool {

    /**离职类型*/
    public static final String QUIT_TYPE = "Q" ;


//    private static SerialNumberTool serialNumberTool = null;
//
//    private SerialNumberTool() {
//    }
//
//    /**
//     * 取得PrimaryGenerater的单例实现
//     *
//     * @return
//     */
//    public static SerialNumberTool getInstance() {
//        if (serialNumberTool == null) {
//            synchronized (SerialNumberTool.class) {
//                if (serialNumberTool == null) {
//                    serialNumberTool = new SerialNumberTool();
//                }
//            }
//        }
//        return serialNumberTool;
//    }

    /**
     * 生成下一个编号
     */
    public static synchronized String newSerialNumber(String type) {

        //生成三位随机数
        Random rnd = new Random();
        int num = 100 + rnd.nextInt(900);

        //格式 type做前缀 + 时间戳 + 三位随机数
        return  type + System.currentTimeMillis() + num ;
    }
}