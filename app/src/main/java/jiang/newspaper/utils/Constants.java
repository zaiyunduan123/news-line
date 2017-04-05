package jiang.newspaper.utils;

/**
 * Created by 在云端 on 2017/3/25.
 * 作用：常量类、配置联网请求地址
 */

public class Constants {
    //联网请求的ip和端口
    public static final String BASE_URL = "http://192.168.155.1:8080/";

    //新闻中心的网络地址
//    public static final String NEWSCENTER_PAGER_URL=BASE_URL+
// "  ";
//    public static final String NEWSCENTER_PAGER_URL= "http://v.juhe.cn/toutiao/index?type=top&key=95c62af049ddc0ab15fc1718b4842f5e";
    public static final String NEWSCENTER_PAGER_URL = BASE_URL + "web_home/static/api/news/categories.json";


}
