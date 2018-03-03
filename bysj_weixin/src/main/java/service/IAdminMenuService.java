package service;

import model.menu.Button;

import java.util.List;

/**
 * Created by liqiao on 2018/1/28.
 * 后台网站的菜单的service
 */
public interface IAdminMenuService {
    /**
     * 查询全部菜单
     * @param level
     */
    List<Button> queryByLevel(String level);

    /**
     * 增加一个菜单按钮
     */
    String insertOne(Button button);

    /**
     * 删除一个菜单按钮，若删除的是一级菜单，则删除一级菜单下所有二级菜单
     */
    String deleteById(String id);
    /**
     * 修改菜单按钮
     */
    String update(Button button);
}
