package com.haipeng.decoration.manager.constant;

/**
 * Created by Administrator on 2017/7/29.
 */

public class UrlConstant {
    public static boolean isDebug = false;


    //    public static String api_url = "http://192.168.43.121:8080";//手机共享
//    public static String api_url = "http://192.168.101.12:8080"; // 公司R01路由
//    public static String api_url = "http://192.168.8.4:8080"; // 宿舍路由
    public static String api_url = "http://59.110.221.173:8080"; // ali_cloud

    public static String UPLOAD_IMAGE = "";
    public static String qiniuImageAdress = "";


    public static String URL_ADD_USER = api_url + "/148124/user/addUser";
    public static String URL_QUERY_USER = api_url + "/148124/user/getUserAll";

    public static String URL_ADD_MASTER = api_url + "/master/addUser";
    public static String URL_QUERY_MASTER = api_url + "/master/getAllMaster";

    public static String URL_ADD_VENDOR = api_url + "/vendor/addVendor";
    public static String URL_QUERY_VENDOR = api_url + "/vendor/getAllVendor";

    public static String URL_ADD_TEMPLATE = api_url + "/template/addTemplate";
    public static String URL_QUERY_TEMPLATE = api_url + "/template/getAllTemplate";

    public static String URL_ADD_RECOMMEND = api_url + "/recommend/addRecommend";
    public static String URL_QUERY_RECOMMEND = api_url + "/recommend/getAllRecommend";

    public static String URL_LOGIN = api_url +"/superUser/findByNamePassword";

}
