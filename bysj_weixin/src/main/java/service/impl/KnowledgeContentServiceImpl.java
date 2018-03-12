package service.impl;

import dao.ContentMapper;
import model.knowledge.Catalog;
import model.knowledge.Content;
import org.springframework.stereotype.Service;
import service.IKnowledgeContentService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by liqiao on 2018/1/27.
 * 后台网站对菜单的管理的service
 */
@Service(value="knowledgeContentService")
public class KnowledgeContentServiceImpl implements IKnowledgeContentService {
    @Resource
    private ContentMapper contentMapper;

    /**
     * @param library
     * @return
     */
    public List<Content> queryByLibrary(String library) {
        return contentMapper.queryByLibrary(library);
    }
    /**
     * @param id
     * @return
     */
    public Content queryById(int id) {
        return contentMapper.queryById(id);
    }

    public int insertOne(Content content) {
        Date createTime = new Date();
        content.setCreateTime(createTime);
        contentMapper.insertOne(content);

        return contentMapper.queryByTime(createTime).getId();
    }

    public void deleteById(int id) {
        contentMapper.deleteById(id);
    }

    /**
     *
     * @param library 库名
     * @param code 一级目录的code
     * @return
     */
    public List<Content> queryByCatalogFirst(String library, String code) {
        Catalog catalog = new Catalog();
        catalog.setLibrary(library);
        catalog.setCode(code);
        return contentMapper.queryByCatalogFirst(catalog);
    }
    /**
     *
     * @param library 库名
     * @param code 二级目录的code
     * @return
     */
    public List<Content> queryByCatalogSecond(String library, String code) {
        Catalog catalog = new Catalog();
        catalog.setLibrary(library);
        catalog.setCode(code);
        return contentMapper.queryByCatalogSecond(catalog);
    }
}
