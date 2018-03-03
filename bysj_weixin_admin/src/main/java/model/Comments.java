package model;

import java.util.Date;

/**
 * Created by liqiao on 2018/1/9.
 * 动态的评论的实体类
 */
public class Comments {
    private String momentsTime;
    private String name;
    private String contentDetail;
    private Date createTime;




    public String getMomentsTime() {
        return momentsTime;
    }

    public void setMomentsTime(String momentsTime) {
        this.momentsTime = momentsTime;
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getContentDetail() {
        return contentDetail;
    }

    public void setContentDetail(String contentDetail) {
        this.contentDetail = contentDetail;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
