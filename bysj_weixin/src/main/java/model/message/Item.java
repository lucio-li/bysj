package model.message;

/**
 * Created by liqiao on 2018/3/1.
 * 图文消息中的item标签内容，为了拼凑xml
 */
public class Item {
    private String Title;
    private String Description;
    private String PicUrl;
    private String Url;
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }


}
