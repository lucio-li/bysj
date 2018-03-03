package model.menu;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqiao on 2018/1/21.
 */
public class Button {
    private String id;
    //菜单类型
    private String type;
    //菜单名称
    private String name;
    //二级菜单
    private List<Button> sub_button;

    private String url;

    private String media_id;
    private String parent_id;
    private String level;

    private String key;
    private String parent_name;
    private String msg_type;

    public String getMsg_type() {
        return msg_type;
    }

    public void setMsg_type(String msg_type) {
        this.msg_type = msg_type;
    }

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Button> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<Button> sub_button) {
        this.sub_button = sub_button;
    }

    @Test
    public void test() {
        //Menu menu = new Menu();
        Button button = new Button();

        button.setName("1");

        Button button2 = new Button();
        button2.setType("1");
        button2.setName("1");
        button2.setUrl("1");
        Button button3 = new Button();
        button3.setType("1");
        button3.setName("1");
        button3.setUrl("1");
        List<Button> list = new ArrayList<Button>();
        list.add(button2);
        list.add(button3);
        button.setSub_button(list);
        //menu.setButton(new Button[]{button});
        String result = JSONObject.toJSONString(button);
        System.out.println(result);

    }
}
