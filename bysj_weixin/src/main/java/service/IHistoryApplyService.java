package service;

import model.knowledge.Content;

/**
 * Created by liqiao on 2018/1/28.
 * 申请入库的申请人的service
 */
public interface IHistoryApplyService {

    void insertPass(Content content);
    void insertRefuse(Content content);
//    List<Content> queryAll();
//    Content queryById(int id);
//    void deleleById(int id);
}
