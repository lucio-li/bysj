package service.impl;

import com.alibaba.fastjson.JSONObject;
import dao.ArticlesMapper;
import model.message.Article;
import org.springframework.stereotype.Service;
import service.IAdminArticlesService;
import utils.WeixinUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqiao on 2018/1/27.
 * 后台网站对菜单的管理的service
 */
@Service(value="adminArticlesService")
public class AdminArticlesServiceImpl implements IAdminArticlesService {

    @Resource
    private ArticlesMapper articlesMapper;

    //添加图文素材
    public void addOne(Article article) {
        article.setShow_cover_pic("1");
        article.setContent_source_url("##");
        JSONObject jsonObject = new JSONObject();
        List<Article> list = new ArrayList<Article>();
        list.add(article);
        jsonObject.put("articles", list);
        String result = WeixinUtil.createArticles(jsonObject.toJSONString());
        JSONObject jsonMedia = JSONObject.parseObject(result);
        String media_id = jsonMedia.getString("media_id");
        article.setMedia_id(media_id);
        article.setAuthor("li");
        String key = System.currentTimeMillis() + "";
        article.setKey(key);
        articlesMapper.addOne(article);
    }

    /**
     * 查询所有图文素材
     */
    public List<Article> queryAll() {
        return articlesMapper.queryAll();
    }


}
