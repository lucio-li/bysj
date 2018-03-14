package service.impl;

import dao.WxUserMapper;
import model.human.WxUser;
import org.springframework.stereotype.Service;
import service.IWxUserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liqiao on 2018/3/13.
 */
@Service("wxUserService")
public class WxUserServiceImpl implements IWxUserService{
    @Resource
    private WxUserMapper wxUserMapper;


    public void insertOne(WxUser wxUser) {
        wxUserMapper.insertOne(wxUser);
    }

    public WxUser queryByOpenid(String openid) {
        return  wxUserMapper.queryByOpenid(openid);
    }
    public void deleteUser(String openid) {
        wxUserMapper.deleteUser(openid);
    }
    public void update(WxUser wxUser) {
        wxUserMapper.update(wxUser);
    }

    public void updateSignTime(WxUser wxUser) {
        wxUserMapper.updateSignTime(wxUser);
    }

    public List<WxUser> querySignUser() {
        return wxUserMapper.querySignUser();
    }
    public List<WxUser> queryUnSignUser() {
        return wxUserMapper.queryUnSignUser();
    }
    public List<WxUser> queryAll() {
        return wxUserMapper.queryAll();
    }
}
