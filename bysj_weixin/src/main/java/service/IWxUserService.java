package service;

import model.human.WxUser;

import java.util.List;

/**
 * Created by liqiao on 2018/3/13.
 */
public interface IWxUserService {
    void insertOne(WxUser wxUser);
    WxUser queryByOpenid(String openid);
    void update(WxUser wxUser);
    void updateSignTime(WxUser wxUser);
    List<WxUser> querySignUser();
    List<WxUser> queryUnSignUser();
    List<WxUser> queryAll();
    void deleteUser(String openid);
}
