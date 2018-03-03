package service;

import model.knowledge.Content;

import java.util.List;

/**
 * Created by liqiao on 2018/1/28.
 * 知识库的具体内容的service
 */
public interface IKnowledgeContentService {

    List<Content> queryByLibrary(String library);
    List<Content> queryByCatalogFirst(String library, String parent_code);


}
