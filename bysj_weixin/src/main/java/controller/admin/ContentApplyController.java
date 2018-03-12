package controller.admin;

import model.knowledge.ApplyUser;
import model.knowledge.Catalog;
import model.knowledge.Content;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.*;
import utils.ResponseUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by liqiao on 2018/3/7.
 */
@Controller
@RequestMapping("/content/apply")
public class ContentApplyController {
    @Resource
    private IKnowledgeCatalogService knowledgeCatalogService;
    @Resource
    private IApplyUserService applyUserService;
    @Resource
    private IContentApplyService contentApplyService;
    @Resource
    private IKnowledgeContentService knowledgeContentService;
    @Resource
    private IHistoryApplyService historyApplyService;

    @RequestMapping(value = "/add")
    public String add(Model model) {
        List<String> libraryList = knowledgeCatalogService.queryLibrary();
        model.addAttribute("libraryList", libraryList);

        //默认是知识库
        List<Catalog> catalogFirst = knowledgeCatalogService.queryCatalogByLibrary("知识库");
        model.addAttribute("catalogFirst", catalogFirst);
        return "content_apply";
    }
    @RequestMapping(value = "/newApply")
    public void newApply(Content content, ApplyUser user, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        try {
            int applyUserId = applyUserService.insertOne(user);
            content.setUser_id(applyUserId);
            contentApplyService.insertOne(content);
            jsonObject.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("result", "error");
        }
        ResponseUtils.renderJson(response, jsonObject.toString());
    }
    //后台的申请列表的controller
    @RequestMapping(value = "/list")
    public String list(Model model) {
        List<Content> contentList = contentApplyService.queryAll();

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
        model.addAttribute("contentList", contentList);
        return "apply_new";
    }

    @RequestMapping(value = "getApply")
    public void getApply(String id, HttpServletResponse response) {
        try {
            Content content = contentApplyService.queryById(Integer.parseInt(id));
            String path = knowledgeCatalogService.getPathById(Integer.parseInt(content.getCatalog_id()));
            content.setPath(path);
            JSONObject jsonObject = new JSONObject();

            JSONObject contentObject = JSONObject.fromObject(content);
            if (content.getUser_id() != 0) {//有填申请人信息
                ApplyUser applyUser = applyUserService.queryById(content.getUser_id());
                JSONObject userObject = JSONObject.fromObject(applyUser);
                jsonObject.put("applyUser", userObject.toString());
            }
            jsonObject.put("content", contentObject.toString());

            ResponseUtils.renderJson(response, jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //审核通过新申请的知识
    @RequestMapping(value = "pass")
    public void pass(Content content, String applyId, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        //插入content的表, 并删除申请的表的数据，同时把数据存入历史记录表
        try {
            knowledgeContentService.insertOne(content);
            Content contentApply = contentApplyService.queryById(Integer.parseInt(applyId));
            contentApplyService.deleleById(Integer.parseInt(applyId));
            historyApplyService.insertPass(contentApply);
            jsonObject.put("result", "success");
            ResponseUtils.renderJson(response, jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("result", "error");
            ResponseUtils.renderJson(response, jsonObject.toString());
        }
    }
    @RequestMapping(value = "refuse")
    public void refuse(String applyId, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        //删除申请的表的数据，同时把数据存入历史记录表
        try {
            contentApplyService.deleleById(Integer.parseInt(applyId));
            Content contentApply = contentApplyService.queryById(Integer.parseInt(applyId));
            historyApplyService.insertRefuse(contentApply);
            jsonObject.put("result", "success");
            ResponseUtils.renderJson(response, jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("result", "error");
            ResponseUtils.renderJson(response, jsonObject.toString());
        }
    }
}
