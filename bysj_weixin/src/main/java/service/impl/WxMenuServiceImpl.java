package service.impl;

import com.alibaba.fastjson.JSONObject;
import dao.ButtonMapper;
import model.menu.Button;
import model.menu.Menu;
import org.springframework.stereotype.Service;
import service.IWxMenuService;
import utils.AppConfig;
import utils.WeixinUtil;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liqiao on 2018/1/27.
 * 微信公众号的菜单的service
 */
@Service(value="wxMenuService")
public class WxMenuServiceImpl implements IWxMenuService{
    @Resource
    private ButtonMapper buttonMapper;

    /**
     * 创建菜单
     */

    public void createMenu() {
        List<Button> btnList = buttonMapper.queryAllForMenu();
        JSONObject menu = new JSONObject();
        menu.put("button", btnList);
        //菜单组装完，向微信的接口发送请求实现
        System.out.println(menu.toJSONString());
        System.out.println("access_token" + AppConfig.access_token);
        String result = WeixinUtil.createMenu(AppConfig.access_token, menu.toJSONString());
        System.out.println("创建菜单" + result);
    }

    /**
     * 查询菜单
     * @return
     */
    public Menu queryMenu() {
        List<Button> button = buttonMapper.queryAll();
        String result = JSONObject.toJSONString(button);
        System.out.println(result);
        return null;
    }

    public void deleteMenu() {

    }
}
