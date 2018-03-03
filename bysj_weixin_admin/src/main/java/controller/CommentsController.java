package controller;

import model.Comments;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.CommentsService;
import utils.ResponseUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * 朋友圈的评论的controller
 * Created by lq on 2017/12/13.
 */
@Controller
@RequestMapping("/comments")
public class CommentsController {
    @Resource
    private CommentsService commentsService;

    /**
     *
     * @param comments
     * @param response
     * 新增评论
     * @return
     */
    @RequestMapping("add")
    public String  add(Comments comments, HttpServletResponse response) {
        String  result = null;
        try {
            result = commentsService.addOne(comments);
        } catch (Exception e) {
            e.printStackTrace();
            result = "fail";
        }

//        ObjectMapper mapper = new ObjectMapper();
//        String str = mapper.writeValueAsString(momentsList);
//        ResponseUtils.renderJson(response, str);


        ResponseUtils.renderJson(response, "{\"result\":\"" + result + "\"}");
        return null;
    }

    /**
     * 删除一条动态
     */
    @RequestMapping("deleteOne")
    public String deleteOne(HttpServletResponse response, String id) {
        if (id == null || "".equals(id.trim())) {
            ResponseUtils.renderJson(response, "{\"result\": \"fail\"}");
            return null;
        }
        String result = commentsService.deleteOne(Integer.valueOf(id));
        ResponseUtils.renderJson(response, "{\"result\": \"" + result + "\"}");
        return null;
    }
}
