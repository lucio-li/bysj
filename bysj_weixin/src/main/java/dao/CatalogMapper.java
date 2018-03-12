package dao;

import model.knowledge.Catalog;

import java.util.List;

/**
 * Created by liqiao on 2018/1/27.
 */
public interface CatalogMapper {

    List<Catalog> queryAll(Catalog catalog);
    List<String> queryLibrary();
    List<Catalog> queryCatalogByLibrary(String library);
    List<Catalog> queryCatalogSecond(Catalog catalog);
    Catalog queryById(int id);
    Catalog queryByCode(Catalog catalog);
}
