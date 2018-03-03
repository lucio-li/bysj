package service.impl;

import com.alibaba.fastjson.JSONObject;
import dao.ButtonMapper;
import model.menu.Button;
import org.springframework.stereotype.Service;
import service.IAdminMenuService;
import service.IWxMenuService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liqiao on 2018/1/27.
 * 后台网站对菜单的管理的service
 */
@Service(value="adminMenuService")
public class AdminMenuServiceImpl implements IAdminMenuService{
    @Resource
    private ButtonMapper buttonMapper;
    @Resource
    private IWxMenuService iWxMenuService;
    //查询一级菜单或者二级菜单
    public List<Button> queryByLevel(String level) {
        return buttonMapper.queryByLevel(level);
    }
    //添加菜单按钮
    public String insertOne(Button button) {
        String level = button.getLevel();
        JSONObject jsonObject = new JSONObject();
        if ("1".equals(level)) {
            List<Button> buttonList = buttonMapper.queryByLevel(button.getLevel());
            if (buttonList.size() >= 3) {//一级菜单数量只能三个以内
                jsonObject.put("result", "fail");
                jsonObject.put("msg", "一级菜单数量不能超三个");
                return  jsonObject.toJSONString();//参数错误无法插入
            }
        } else if ("2".equals(level)) {
            List<Button> buttonList = buttonMapper.queryByParentId(button.getParent_id());
            if (buttonList.size() >= 5) {//二级菜单数量只能五个以内
                jsonObject.put("result", "fail");
                jsonObject.put("msg", "二级菜单数量不能超五个");
                return jsonObject.toJSONString();//参数错误无法插入
            }
        } else {
            jsonObject.put("result", "fail");
            jsonObject.put("msg", "请选择新增菜单按钮的级别");
            return jsonObject.toJSONString();//参数错误无法插入
        }
        buttonMapper.insertOne(button);
        jsonObject.put("result", "success");
        jsonObject.put("msg", "新增菜单按钮成功");
        iWxMenuService.createMenu();//新增菜单之后就向微信公众号刷新菜单

        return jsonObject.toJSONString();
    }

    public String deleteById(String id) {
        JSONObject jsonObject = new JSONObject();
        try {
            buttonMapper.deleteById(id);
            jsonObject.put("result", "success");
            jsonObject.put("msg", "删除成功");
            iWxMenuService.createMenu();//删除菜单之后就向微信公众号刷新菜单
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("result", "fail");
            jsonObject.put("msg", "删除失败，请稍后重试");

        }
        return jsonObject.toJSONString();
    }
    public String update(Button button) {
        JSONObject jsonObject = new JSONObject();
        try {
            buttonMapper.update(button);
            jsonObject.put("result", "success");
            jsonObject.put("msg", "删除成功");
            iWxMenuService.createMenu();//删除菜单之后就向微信公众号刷新菜单
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("result", "fail");
            jsonObject.put("msg", "修改失败，请稍后重试");

        }
        return jsonObject.toJSONString();
    }

}
