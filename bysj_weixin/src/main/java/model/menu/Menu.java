package model.menu;

import java.util.List;

/**
 * Created by liqiao on 2018/1/21.
 * 菜单
 */
public class Menu {
    //一级菜单
    private List<Button> button;

    public List<Button> getButton() {
        return button;
    }

    public void setButton(List<Button> button) {
        this.button = button;
    }
}
