package dao;

import model.menu.Button;

import java.util.List;

/**
 * Created by liqiao on 2018/1/27.
 */
public interface ButtonMapper {
    void insertOne(Button button);
    List<Button> queryAllForMenu();
    List<Button> queryAll();
    List<Button> queryByKey(String key);
    /**
     * 查询一级菜单或者二级菜单
     */
    List<Button> queryByLevel(String level);
    /**
     * 由一级菜单的id和level, 查询二级菜单
     */
    List<Button> queryByParentId(String parentId);
    void deleteById(String id);
    void update(Button button);
}
