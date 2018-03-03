package service;

import model.menu.Menu;

/**
 * Created by liqiao on 2018/1/27.
 * 微信的菜单的service
 */
public interface IWxMenuService {
    /**
     * 创建菜单, 从数据库拿数据
     */
    void createMenu();
    /**
     * 查询菜单
     */
    Menu queryMenu();

    /**
     * 删除菜单
     */
    void deleteMenu();



}
