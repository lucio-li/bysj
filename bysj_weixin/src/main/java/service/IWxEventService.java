package service;

import java.util.Map;

/**
 * Created by liqiao on 2018/1/28.
 */
public interface IWxEventService {
    //菜单的ClICK事件
    String menuClick(Map<String, String> req);

}
