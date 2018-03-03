package controller.admin;

import model.menu.Button;
import model.message.Article;
import model.message.Image;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.IAdminArticlesService;
import service.IAdminImageService;
import service.IAdminMenuService;
import utils.ResponseUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by liqiao on 2018/2/5.
 * 后台模版的菜单相关的controller
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private IAdminMenuService adminMenuService;
    @Resource
    private IAdminImageService adminImageService;
    @Resource
    private IAdminArticlesService adminArticlesService;
    /**
     * 菜单列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(Model model, String level) {

        List<Button> buttonList = adminMenuService.queryByLevel(level);
        model.addAttribute("buttonList", buttonList);

        if ("2".equals(level)) {
            List<Button> buttonFirstList = adminMenuService.queryByLevel("1");//查询一级菜单列表
            model.addAttribute("buttonFirstList", buttonFirstList);
        }
        List<Image> imageList = adminImageService.queryAll();
        List<Article> articlesList = adminArticlesService.queryAll();
        model.addAttribute("imageList", imageList);
        model.addAttribute("articlesList", articlesList);
        model.addAttribute("level", level);
        return "menu_list";
    }
    @RequestMapping(value = "/update")
    public String update(Model model, String level) {

//        List<Button> buttonList = adminMenuService.queryByLevel(level);
//        System.out.println(buttonList.size());
//        System.out.println(buttonList.get(0).getName());
        model.addAttribute("buttonList", "");
        return "menu_update";
    }

    /**
     * 新增菜单按钮
     * @param button
     * @param res
     */
    @RequestMapping("/addButton")
    public void addMenuButton(Button button, HttpServletResponse res) {
        String result = adminMenuService.insertOne(button);

        ResponseUtils.renderJson(res, result);
    }
    /**
     * 删除菜单按钮
     * @param button
     * @param res
     */
    @RequestMapping("/deleteButton")
    public void deleteButton(Button button, HttpServletResponse res) {
        String result = adminMenuService.deleteById(button.getId());
        ResponseUtils.renderJson(res, result);
    }
    /**
     * 删除菜单按钮
     * @param button
     * @param res
     */
    @RequestMapping("/updateButton")
    public void updateButton(Button button, HttpServletResponse res) {
        String result = adminMenuService.update(button);
        ResponseUtils.renderJson(res, result);
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
}
