package dao;

import model.message.Article;

import java.util.List;

/**
 * Created by liqiao on 2018/1/27.
 */
public interface ArticlesMapper {
    void addOne(Article article);

    /**
     * 查询所有的图文信息
     */
    List<Article> queryAll();
    Article queryByKey(String key);
}
