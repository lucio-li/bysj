package model.message;

/**
 * Created by liqiao on 2018/1/27.
 * 文本消息
 */
public class TextMessage extends BaseWechatMessage {
    /**
     * 文本消息内容
     */
    private String Content;
    private String EventKey;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
}