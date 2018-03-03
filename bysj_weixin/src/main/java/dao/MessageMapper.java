package dao;

import model.message.TextMessage;

/**
 * Created by liqiao on 2018/1/28.
 */
public interface MessageMapper {
    TextMessage queryOneByKey(String eventKey);
}
