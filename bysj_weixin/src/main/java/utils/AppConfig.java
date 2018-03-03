package utils;

/**
 * Created by liqiao on 2018/1/21.
 */
public class AppConfig {
    public static final String TOKEN = "123456";//填写的token
    public static String access_token = "";//调用微信接口要用的access_token
    //public static final String appid = "wx2d2f0c8d33438071";//公众号的appid
    //public static final String appsecret = "9790c0b6e1c2e7f13505ff5dfbfc3c59";
    //测试号
    public static final String appid = "wx8b685c848534e218";//公众号的appid
    public static final String appsecret = "d4624c36b6795d1d99dcf0547af5443d";
    public static final String wx_token_url = "https://api.weixin.qq.com/cgi-bin/token";

    //新增永久图文素材
    public static final String wx_material_new = "https://api.weixin.qq.com/cgi-bin/material/add_news";

}
