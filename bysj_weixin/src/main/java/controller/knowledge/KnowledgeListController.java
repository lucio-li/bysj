package controller.knowledge;

import model.knowledge.Catalog;
import model.knowledge.Content;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.IKnowledgeCatalogService;
import service.IKnowledgeContentService;
import utils.ResponseUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by liqiao on 2018/3/2.
 * 知识库的controller类
 */
@Controller
@RequestMapping("/knowledge")
public class KnowledgeListController {
    @Resource
    private IKnowledgeCatalogService knowledgeCatalogService;
    @Resource
    private IKnowledgeContentService knowledgeContentService;
    //知识库首页
    @RequestMapping(value = "/list")
    public String list(Model model) {
        List<String> libraryList = knowledgeCatalogService.queryLibrary();
        model.addAttribute("libraryList", libraryList);

        //默认是知识库
        List<Catalog> catalogFirst = knowledgeCatalogService.queryCatalogByLibrary("知识库");
        model.addAttribute("catalogFirst", catalogFirst);

        //查询默认库的所有内容
        //默认是知识库
        List<Content> contentList = knowledgeContentService.queryByLibrary("知识库");
        model.addAttribute("contentList", contentList);
        return "knowledge_list";
    }
    //根据库名查询一级目录
    @RequestMapping(value = "/getCatalogFirst")
    public void getCatalogFirst(String library, HttpServletResponse response) {
        //默认是知识库
        List<Catalog> catalogFirst = knowledgeCatalogService.queryCatalogByLibrary(library);

        String catalogFirstStr = JSONArray.fromObject(catalogFirst).toString();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("catalogFirst", catalogFirstStr);
        ResponseUtils.renderJson(response, jsonObject.toString());

    }
    //根据查询二级目录
    @RequestMapping(value = "/getCatalogSecond")
    public void getCatalogSecond(Catalog catalog, HttpServletResponse response) {
        //默认是知识库
        List<Catalog> catalogSecond = knowledgeCatalogService.queryCatalogSecond(catalog);

        String catalogSecondStr = JSONArray.fromObject(catalogSecond).toString();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("catalogSecond", catalogSecondStr);
        ResponseUtils.renderJson(response, jsonObject.toString());

    }
    /**
     * 根据库类别查询所有内容
     */
    @RequestMapping(value = "/getContentByLibrary")
    public void getContentByLibrary(Catalog catalog, HttpServletResponse response) {

        List<Content> contentList = knowledgeContentService.queryByLibrary(catalog.getLibrary());
        for(Content content : contentList) {
            if (content.getDescription() == null || "".equals(content.getDescription())) {
                content.setDescription(content.getContent());

            }
            if (content.getDescription().length() > 40) {
                content.setDescription(content.getDescription().substring(0,38) + "......");
            }
            if (content.getTitle().length() > 13) {
                content.setTitle(content.getTitle().substring(0, 11) + "...");
            }
            String path = knowledgeCatalogService.getPathById(Integer.parseInt(content.getCatalog_id()));
            content.setPath(path);
        }
        String contentListStr = JSONArray.fromObject(contentList).toString();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("contentList", contentListStr);
        ResponseUtils.renderJson(response, jsonObject.toString());

    }


    /**
     * 根据库类别，一级目录的code查询所有内容
     */
    //根据查询二级目录
    @RequestMapping(value = "/getContentFirst")
    public void getContentFirst(Catalog catalog, HttpServletResponse response) {
        //默认是知识库
        List<Content> contentList = knowledgeContentService.queryByCatalogFirst(catalog.getLibrary(), catalog.getCode());
        for(Content content : contentList) {
            if (content.getDescription() == null|| "".equals(content.getDescription())) {
                content.setDescription(content.getContent());

            }
            if (content.getDescription().length() > 40) {
                content.setDescription(content.getDescription().substring(0,38) + "......");
            }
            if (content.getTitle().length() > 13) {
                content.setTitle(content.getTitle().substring(0, 11) + "...");
            }
            String path = knowledgeCatalogService.getPathById(Integer.parseInt(content.getCatalog_id()));
            content.setPath(path);
        }
        String contentListStr = JSONArray.fromObject(contentList).toString();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("contentList", contentListStr);
        ResponseUtils.renderJson(response, jsonObject.toString());

    }

    /**
     * 根据库类别，二级目录的code查询所有内容
     */
    //根据查询二级目录
    @RequestMapping(value = "/getContentSecond")
    public void getContentSecond(Catalog catalog, HttpServletResponse response) {
        //默认是知识库
        List<Content> contentList = knowledgeContentService.queryByCatalogSecond(catalog.getLibrary(), catalog.getCode());
        for(Content content : contentList) {
            if (content.getDescription() == null || "".equals(content.getDescription())) {
                content.setDescription(content.getContent());

            }
            if (content.getDescription().length() > 40) {
                content.setDescription(content.getDescription().substring(0,38) + "......");
            }
            if (content.getTitle().length() > 13) {
                content.setTitle(content.getTitle().substring(0, 11) + "...");
            }
            String path = knowledgeCatalogService.getPathById(Integer.parseInt(content.getCatalog_id()));
            content.setPath(path);
        }
        String contentListStr = JSONArray.fromObject(contentList).toString();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("contentList", contentListStr);
        ResponseUtils.renderJson(response, jsonObject.toString());

    }


}
