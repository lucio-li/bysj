package service;

import model.message.Article;

import java.util.List;

/**
 * Created by liqiao on 2018/1/28.
 * 后台网站的图文的service
 */
public interface IAdminArticlesService {
    /**
     * 增加图文素材
     */
    void addOne(Article article);

    /**
     * 查询所有的素材
     */
    List<Article> queryAll();

}
