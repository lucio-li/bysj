package service.impl;

import dao.CatalogMapper;
import model.knowledge.Catalog;
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

    //根据id获取目录path
    public String getPathById(int id) {
        Catalog catalog = catalogMapper.queryById(id);
        String path = catalog.getLibrary();
        Catalog catalogTmp = catalogMapper.queryByCode(catalog);
        path += "->" + catalogTmp.getName() + "->" + catalog.getName();
        return path;
    }

}
