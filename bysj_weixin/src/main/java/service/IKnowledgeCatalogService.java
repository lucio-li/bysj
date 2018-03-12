package service;

import model.knowledge.Catalog;

import java.util.List;

/**
 * Created by liqiao on 2018/1/28.
 * 知识库的页面的目录的service
 */
public interface IKnowledgeCatalogService {

    /**
     * 查询所有
     */
    List<Catalog> queryAll(Catalog catalog);

    /**
     * 查询所有的库种类
     * @return
     */
    public List<String> queryLibrary();

    /**
     * 根据库名查询一级目录
     */
    public List<Catalog> queryCatalogByLibrary(String library);
    /**
     * 查询二级目录
     */
    public List<Catalog> queryCatalogSecond(Catalog catalog);
    //根据id获取目录path
    public String getPathById(int id);
}
