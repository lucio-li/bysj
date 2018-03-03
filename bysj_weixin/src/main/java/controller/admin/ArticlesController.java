package controller.admin;

import com.alibaba.fastjson.JSONObject;
import model.message.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.IAdminArticlesService;
import utils.ResponseUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liqiao on 2018/2/19.
 * 图片素材上传
 */
@Controller
@RequestMapping("/articles")
public class ArticlesController {
    @Resource
    private IAdminArticlesService adminArticlesService;
    //上传图文素材
    @RequestMapping("/add")
    public void add(Article article, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        try {
            adminArticlesService.addOne(article);
            jsonObject.put("msg", "success");
            ResponseUtils.renderJson(response, jsonObject.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("msg", "fail");
            ResponseUtils.renderJson(response, jsonObject.toJSONString());
        }

//        adminImageService.addOne(multipartFile, response);
    }


}
