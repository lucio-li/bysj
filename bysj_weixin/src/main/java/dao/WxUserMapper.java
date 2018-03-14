package dao;

import model.human.WxUser;

import java.util.List;

/**
 * Created by liqiao on 2018/1/27.
 */
public interface WxUserMapper {
    void insertOne(WxUser wxUser);
    WxUser queryByOpenid(String openid);
    void update(WxUser wxUser);
    void updateSignTime(WxUser wxUser);
    List<WxUser> querySignUser();
    List<WxUser> queryUnSignUser();
    List<WxUser> queryAll();
    void deleteUser(String openid);
}
