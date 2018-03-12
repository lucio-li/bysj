package controller.admin;

import model.knowledge.Catalog;
import model.knowledge.Content;
import model.knowledge.ContentAttachment;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import service.IContentAttachmentService;
import service.IKnowledgeCatalogService;
import service.IKnowledgeContentService;
import utils.ResponseUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by liqiao on 2018/3/5.
 * 知识库管理
 */
@Controller
@RequestMapping("/admin/knowledge")
public class KnowledgeController {
    @Resource
    private IKnowledgeCatalogService knowledgeCatalogService;
    @Resource
    private IKnowledgeContentService knowledgeContentService;
    @Resource
    private IContentAttachmentService contentAttachmentService;

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
        return "admin_knowledge_list";
    }

    @RequestMapping(value = "/addFile")
    public void addFile(Content content, @RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {

        int id = knowledgeContentService.insertOne(content);
        ContentAttachment attachment = null;
        if (file != null) {
            attachment = uploadFile(file, request);
            if (attachment != null) {
                attachment.setContent_id(id);
                contentAttachmentService.insertOne(attachment);
            }
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", "success");
        ResponseUtils.renderJson(response, jsonObject.toString());
    }
    @RequestMapping(value = "/add")
    public void add(Content content, HttpServletRequest request, HttpServletResponse response) {

        int id = knowledgeContentService.insertOne(content);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", "success");
        ResponseUtils.renderJson(response, jsonObject.toString());
    }


    @RequestMapping(value = "/upload")
    public void upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response)  {
        //项目部署的路径
        String filePath = request.getSession().getServletContext().getRealPath("/");
        filePath += "WEB-INF/fileTmp";
        File filepath = new File(filePath);
        if(!filepath.exists()){
            filepath.mkdir();
        }
        JSONObject jsonObject = new JSONObject();

        if(!file.isEmpty()) {
            //上传文件名
            String filename = file.getOriginalFilename();

            //将上传文件保存到一个目标文件当中
            try {
                file.transferTo(new File(filePath + File.separator + filename));
                jsonObject.put("result", "success");
            } catch (IOException e) {
                e.printStackTrace();
                jsonObject.put("result", "error");
            }

        } else {
            jsonObject.put("result", "error");
        }
        ResponseUtils.renderJson(response, jsonObject.toString());
    }

    @RequestMapping(value = "/delete")
    public void delete(Content content, HttpServletResponse response) {
        knowledgeContentService.deleteById(content.getId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", "success");
        ResponseUtils.renderJson(response, jsonObject.toString());
    }

    public ContentAttachment uploadFile(MultipartFile file, HttpServletRequest request) {
        String filePath = request.getSession().getServletContext().getRealPath("/");
        filePath += "WEB-INF/fileTmp";
        File filepath = new File(filePath);
        ContentAttachment attachment = new ContentAttachment();
        if(!filepath.exists()){
            filepath.mkdir();
        }
        JSONObject jsonObject = new JSONObject();

        if(!file.isEmpty()) {
            //上传文件名
            String filename = file.getOriginalFilename();

            long time = System.currentTimeMillis();
            //将上传文件保存到一个目标文件当中
            try {
                file.transferTo(new File(filePath + File.separator + time + filename));
                attachment.setFilename(filename);
                attachment.setLocal_path(filePath + File.separator + time + filename);

            } catch (IOException e) {
                e.printStackTrace();
                attachment = null;
            }

        } else {
            jsonObject.put("result", "error");
        }
        return attachment;
    }
    //历史申请列表
    @RequestMapping(value = "/history")
    public String history(Model model) {
        return "history_apply";
    }
}
