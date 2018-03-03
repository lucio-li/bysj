package dao;

import model.knowledge.Catalog;
import model.knowledge.Content;

import java.util.List;

/**
 * Created by liqiao on 2018/1/27.
 */
public interface ContentMapper {

    List<Content> queryByLibrary(String library);
    List<Content> queryByCatalogFirst(Catalog catalog);

}
