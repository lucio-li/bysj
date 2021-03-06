package dao;

import model.knowledge.Content;

import java.util.List;

/**
 * Created by liqiao on 2018/1/27.
 */
public interface HistoryApplyMapper {
    void insertPass(Content content);
    void insertRefuse(Content content);
    List<Content> queryAll();
    Content queryById(int id);
    void deleleById(int id);
}
