package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import model.User;
import service.UserService;
import utils.HttpRequestUtils;
import utils.ResponseUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lq on 2017/12/11.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/add")
    public String  index(HttpServletRequest request, HttpServletResponse response){
    	User user = new User();
    	user.setUserId(request.getParameter("userId"));
    	user.setName(request.getParameter("name"));
    	user.setOpenid(request.getParameter("openid"));
        System.out.println(user.getOpenid());
        System.out.println("增加user");
        try {
            userService.insertOne(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResponseUtils.renderJson(response, "success");
        return null;
    }
    @RequestMapping("/update")
    public String  update(HttpServletRequest request, HttpServletResponse response){
        User user = new User();
        user.setUserId(request.getParameter("userId"));
        user.setName(request.getParameter("name"));
        user.setOpenid(request.getParameter("openid"));
        System.out.println("修改user" + user.getUserId());
        //System.out.println("增加user");
        try {
            userService.updateOne(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResponseUtils.renderJson(response, "{msg:'success'}");
        return null;
    }
    @RequestMapping("/delete")
    public String  delete(HttpServletRequest request, HttpServletResponse response){
        User user = new User();
        user.setOpenid(request.getParameter("openid"));
        System.out.println("删除user" + user.getOpenid());

        try {
            userService.deleteOne(user);

            ResponseUtils.renderJson(response, "{\"success\":\"success\"}");
        } catch (Exception e) {
            e.printStackTrace();
            ResponseUtils.renderJson(response, "{success:'no user'}");
        }
        return null;
    }

//    @RequestMapping("/getOpenid")
//    public String  getOpenid(HttpServletRequest request, HttpServletResponse response){
//
//        String js_code = request.getParameter("js_code");
//        String appid = request.getParameter("appid");
//        String secret = request.getParameter("secret");
//        String grant_type = request.getParameter("grant_type");
//        String str = "js_code=" + js_code + "&appid=" + appid + "&secret=" + secret + "&grant_type=" + grant_type;
//        String openid = HttpRequestUtils.sendGet("https://api.weixin.qq.com/sns/jscode2session", str);
//        System.out.println("openid:" + openid);
//        ResponseUtils.renderJson(response, openid);
//        return null;
//    }
}
