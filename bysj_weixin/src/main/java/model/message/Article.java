package model.message;

/**
 * Created by liqiao on 2018/1/28.
 * 图文素材的类
 */
public class Article extends BaseWechatMessage{
    private String title;
    private String thumb_media_id;
    private String author;
    private String show_cover_pic;
    private String content;
    private String content_source_url;
    private String media_id;
    private String url;
    private String key;
    private String ArticleCount;

    public String getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(String articleCount) {
        ArticleCount = articleCount;
    }



    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb_media_id() {
        return thumb_media_id;
    }

    public void setThumb_media_id(String thumb_media_id) {
        this.thumb_media_id = thumb_media_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getShow_cover_pic() {
        return show_cover_pic;
    }

    public void setShow_cover_pic(String show_cover_pic) {
        this.show_cover_pic = show_cover_pic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent_source_url() {
        return content_source_url;
    }

    public void setContent_source_url(String content_source_url) {
        this.content_source_url = content_source_url;
    }


}
