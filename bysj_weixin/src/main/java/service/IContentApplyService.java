package service;

import model.knowledge.Content;

import java.util.List;

/**
 * Created by liqiao on 2018/1/28.
 * 申请入库的申请人的service
 */
public interface IContentApplyService {

    void insertOne(Content content);
    List<Content> queryAll();
    Content queryById(int id);
    void deleleById(int id);
}
