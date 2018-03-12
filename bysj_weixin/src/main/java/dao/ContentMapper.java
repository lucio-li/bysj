package dao;

import model.knowledge.Catalog;
import model.knowledge.Content;

import java.util.Date;
import java.util.List;

/**
 * Created by liqiao on 2018/1/27.
 */
public interface ContentMapper {
    Content queryById(int id);
    List<Content> queryByLibrary(String library);
    List<Content> queryByCatalogFirst(Catalog catalog);
    List<Content> queryByCatalogSecond(Catalog catalog);
    void insertOne(Content content);
    void deleteById(int id);
    Content queryByTime(Date createTime);
}
