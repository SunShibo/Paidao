//package com.solland.paidao.test;
//
//import com.taobao.diamond.manager.DiamondManager;
//import com.taobao.diamond.manager.ManagerListener;
//import com.taobao.diamond.manager.impl.DefaultDiamondManager;
//
//import java.util.concurrent.Executor;
//
///**
// * Created by sunshibo on 2015/12/29.
// */
//public class DiamondTestClient {
//
//    public static DiamondManager manager;
//    public static void main(String[] str) {
//        initDiamondManager();
//    }
//    private static void initDiamondManager() {
//        manager = new DefaultDiamondManager("DEFAULT_GROUP", "sunshibo", new ManagerListener() {
//            public void receiveConfigInfo(String configInfo) {
//                restart();
//            }
//            public Executor getExecutor() {
//                return null;
//            }
//        });//true表示强制使用域名
//        restart();
//    }
//    private static void restart() {
//        String availableConfigureInfomation = manager.getAvailableConfigureInfomation(5000);
//        System.out.println("availableConfigureInfomation=" + availableConfigureInfomation);
//    }
//}
//
