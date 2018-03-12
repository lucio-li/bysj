package controller.partment;

import model.human.Notice;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.INoticeService;
import utils.ResponseUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liqiao on 2018/3/12.
 * 通知公告的controller
 */
@Controller
@RequestMapping("/human/notice")
public class NoticeController {
    @Resource
    private INoticeService noticeService;
    @RequestMapping("/index")
    public String index(Model model) {
        Notice notice = noticeService.queryOne();
        model.addAttribute("notice", notice);
        return "notice_index";
    }
    @RequestMapping("/list")
    public String list(Model model) {
        Notice notice = noticeService.queryOne();

        model.addAttribute("notice", notice);
        return "notice_list";
    }
    @RequestMapping("/add")
    public void add(Notice notice, HttpServletResponse response) {
        Notice noticeTmp = noticeService.queryOne();
        if (noticeTmp == null) {
            noticeService.insertOne(notice);
        } else {
            noticeTmp.setTitle(notice.getTitle());
            noticeTmp.setAuthor(notice.getAuthor());
            noticeTmp.setContent(notice.getContent());
            noticeService.update(noticeTmp);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", "success");
        ResponseUtils.renderJson(response, jsonObject.toString());
    }

}
