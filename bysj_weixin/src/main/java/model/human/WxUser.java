package model.human;

import java.text.SimpleDateFormat;

/**
 * Created by liqiao on 2018/3/13.
 */
public class WxUser {
    private String openid;
    private String nickname;
    private String headimgurl;
    private String signTime;
    private String username;
    private String timeText;

    public String getTimeText() {
        SimpleDateFormat nowDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return nowDf.format(this.signTime);// new Date()为获取当前系统时间
    }

    public void setTimeText(String timeText) {



        this.timeText = timeText;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
