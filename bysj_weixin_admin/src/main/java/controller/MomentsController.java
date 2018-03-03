package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.MomentsService;
import utils.ResponseUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 朋友圈照片的展示，管理
 * Created by lq on 2017/12/13.
 */
@Controller
@RequestMapping("/moments")
public class MomentsController {
    @Resource
    private MomentsService momentsService;

    /**
     *
     * @param request
     * @param response
     * 获取所有的动态
     * @return
     */
    @RequestMapping("list")
    public String  list(HttpServletRequest request, HttpServletResponse response) {
        String  result = null;

        try {
            result = momentsService.queryAll();

            //System.out.println(momentsList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

//        ObjectMapper mapper = new ObjectMapper();
//        String str = mapper.writeValueAsString(momentsList);
//        ResponseUtils.renderJson(response, str);


        ResponseUtils.renderJson(response, result);
        return null;
    }

    /**
     * 删除一条动态
     */
    @RequestMapping("deleteOne")
    public String deleteOne(HttpServletResponse response, String time) {
        if (time == null || "".equals(time.trim())) {
            ResponseUtils.renderJson(response, "{\"result\": \"fail\"}");
            return null;
        }
        String result = momentsService.deleteOne(time);
        ResponseUtils.renderJson(response, "{\"result\": \"" + result + "\"}");
        return null;
    }
}
