package model.human;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by liqiao on 2018/3/12.
 */
public class Notice {
    private int id;
    private String title;
    private String content;
    private String author;
    private Date createTime;
    private String timeText;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTimeText() {
        DateFormat format3 = DateFormat.getDateInstance(DateFormat.LONG, Locale.SIMPLIFIED_CHINESE);

        return format3.format(this.createTime);
    }

    public void setTimeText(String timeText) {
        this.timeText = timeText;
    }
}
