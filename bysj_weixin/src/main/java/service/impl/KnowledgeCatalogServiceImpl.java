package service.impl;

import dao.CatalogMapper;
import model.knowledge.Catalog;
import org.junit.Test;
import org.springframework.stereotype.Service;
import service.IKnowledgeCatalogService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liqiao on 2018/1/27.
 * 后台网站对菜单的管理的service
 */
@Service(value="knowledgeCatalogService")
public class KnowledgeCatalogServiceImpl implements IKnowledgeCatalogService {
    @Resource
    private CatalogMapper catalogMapper;

    public List<Catalog> queryAll(Catalog catalog) {
        System.out.println("开始test");
        List<String> catalogList = catalogMapper.queryLibrary();
        for (String catalogT : catalogList) {
            System.out.println(catalogT);
        }
        return null;
    }
    public List<String> queryLibrary() {
        return catalogMapper.queryLibrary();
    }
    public List<Catalog> queryCatalogByLibrary(String library) {
        return catalogMapper.queryCatalogByLibrary(library);
    }
    public List<Catalog> queryCatalogSecond(Catalog catalog) {
        return catalogMapper.queryCatalogSecond(catalog);
    }
    @Test
    public void test() {
        queryAll(null);
    }

}
