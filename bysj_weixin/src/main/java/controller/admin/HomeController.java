package controller.admin;

import model.menu.Button;
import model.message.Article;
import model.message.Image;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import service.IAdminArticlesService;
import service.IAdminImageService;
import utils.AppConfig;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqiao on 2018/2/5.
 * 后台模版的页面的controller
 */
@Controller
@RequestMapping("/admin")
public class HomeController {
    @Resource
    private IAdminImageService adminImageService;
    @Resource
    private IAdminArticlesService adminArticlesService;

    @RequestMapping(value = "/")
    public String index(Model model) {
        List<Button> list = new ArrayList<Button>();
        for (int i =0; i < 3; i++) {
            Button button = new Button();
            button.setName("" + i);
            list.add(button);
        }

        model.addAttribute("list", list);
        return "index";
    }
    @RequestMapping(value = "/list")
    public String list(Model model) {
        List<Button> list = new ArrayList<Button>();
        for (int i =0; i < 3; i++) {
            Button button = new Button();
            button.setName("" + i);
            list.add(button);
        }



        model.addAttribute("list", list);
        return "list";
    }
    @RequestMapping(value = "/info")
    public String info(Model model) {

        model.addAttribute("list", "hello");
        return "info";
    }
    @RequestMapping(value = "/pass")
    public String pass(Model model) {

        model.addAttribute("list", "hello");
        return "pass";
    }
    @RequestMapping(value = "/page")
    public String page(Model model) {

        model.addAttribute("list", "hello");
        return "page";
    }
    @RequestMapping(value = "/adv")
    public String adv(Model model) {

        model.addAttribute("list", "hello");
        return "adv";
    }

    @RequestMapping(value = "/book")
    public String book(Model model) {

        model.addAttribute("list", "hello");
        return "book";
    }
    @RequestMapping(value = "/tips")
    public String tips(String url, Model model) {
        System.out.println("url" + url);
        model.addAttribute("url", url);
        return "tips";
    }
    @RequestMapping(value = "/column")
    public String column(Model model) {

        model.addAttribute("list", "hello");
        return "column";
    }
    @RequestMapping(value = "/cate")
    public String cate(Model model, String level) {
        System.out.println("菜单" + level);
        model.addAttribute("list", "hello");
        return "cate";
    }
    @RequestMapping(value = "/add")
    public String add(Model model) {

        model.addAttribute("list", "hello");
        return "add";
    }
    //图片素材
    @RequestMapping(value = "/imagesMessage", method = RequestMethod.GET)
    public String imagesMessageGet(Model model) {
        List<Image> imageList = adminImageService.queryAll();
        model.addAttribute("ACCESS_TOKEN", AppConfig.access_token);
        model.addAttribute("TYPE", "image");
        model.addAttribute("imageList", imageList);
        return "imagesMessage";
    }
    //图片素材
    @RequestMapping(value = "/imagesMessage", method = RequestMethod.POST)
    public String imagesMessagePost(@RequestParam("file") MultipartFile multipartFile, HttpServletResponse response, Model model) {
        adminImageService.addOne(multipartFile, response);

        List<Image> imageList = adminImageService.queryAll();
        model.addAttribute("ACCESS_TOKEN", AppConfig.access_token);
        model.addAttribute("TYPE", "image");
        model.addAttribute("imageList", imageList);
        return "imagesMessage";
    }
    //图文素材
    @RequestMapping(value = "/addArticles")
    public String addArticles(Model model) {
        List<Article> articlesList = adminArticlesService.queryAll();
        List<Image> imageList = adminImageService.queryAll();
        model.addAttribute("ACCESS_TOKEN", AppConfig.access_token);
        model.addAttribute("TYPE", "image");
        model.addAttribute("articlesList", articlesList);
        model.addAttribute("imageList", imageList);
        return "addArticles";
    }

    //微信端的成功提示页
    @RequestMapping(value = "/msgSuccess")
    public String msgSuccess(Model model) {
        return "msg_success";
    }
    //微信端的成失败提示页
    @RequestMapping(value = "/msgWarn")
    public String msgWarn(Model model) {
        return "msg_warn";
    }
}
