package controller.admin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.IAdminImageService;
import utils.ResponseUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liqiao on 2018/2/19.
 * 图片素材上传
 */
@Controller
@RequestMapping("/image")
public class ImageController {
    @Resource
    private IAdminImageService adminImageService;
//    //上传图片
//    @RequestMapping("/add")
//    public void add(@RequestParam("file") MultipartFile multipartFile, HttpServletResponse response) {
//        System.out.println("添加图片");
//        adminImageService.addOne(multipartFile, response);
//    }
    @RequestMapping("/delete")
    public void delete(String id, HttpServletResponse response) {
        try {
            adminImageService.deleteById(id);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result", "success");
            ResponseUtils.renderJson(response, jsonObject.toJSONString());
        } catch (Exception e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result", "fail");
            ResponseUtils.renderJson(response, jsonObject.toJSONString());
        }

    }
    @RequestMapping("/getImg")
    public void getImg(String url, HttpServletResponse response) {
        ResponseUtils.renderImg(response, url);

    }

}
