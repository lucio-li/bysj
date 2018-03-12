package controller.knowledge;

import model.knowledge.Content;
import model.knowledge.ContentAttachment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.IContentAttachmentService;
import service.IKnowledgeCatalogService;
import service.IKnowledgeContentService;

import javax.annotation.Resource;

/**
 * Created by liqiao on 2018/3/3.
 */
@Controller
@RequestMapping("/content")
public class ContentDetailController {
    @Resource
    private IKnowledgeCatalogService knowledgeCatalogService;
    @Resource
    private IKnowledgeContentService knowledgeContentService;
    @Resource
    private IContentAttachmentService contentAttachmentService;
    @RequestMapping(value = "/detail")
    public String detail(String id, Model model) {
        if (id == null) {
            id = "1";
        }


        Content content = knowledgeContentService.queryById(Integer.parseInt(id));

        if (content == null) {
            content = new Content();
            content.setTitle("未知");
            content.setContent("未知");
            model.addAttribute("content", content);
            model.addAttribute("path", "未知");
            return "content_detail";
        }
        model.addAttribute("content", content);
        String path = knowledgeCatalogService.getPathById(Integer.parseInt(content.getCatalog_id()));
        model.addAttribute("path", path);
        //查询附件信息
        ContentAttachment contentAttachment = contentAttachmentService.queryByContentId(Integer.parseInt(id));
        model.addAttribute("attachment", contentAttachment);

        //
        return "content_detail";
    }
}
