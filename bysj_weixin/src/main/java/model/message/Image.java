package model.message;

/**
 * Created by liqiao on 2018/2/25.
 */
public class Image extends BaseWechatMessage{
    private String media_id;
    private String url;
    private String type;
    private String name;
    private String key;
    private Image Image;

    public model.message.Image getImage() {
        return Image;
    }

    public void setImage(model.message.Image image) {
        Image = image;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
