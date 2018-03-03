package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Moments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import service.UploadService;
import utils.ResponseUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/upload")
public class UploadController {
	@Resource
    private UploadService uploadService;
    @Autowired
    private HttpServletRequest request;
    private  HttpServletResponse response;
    @RequestMapping("/content")
    public String  content(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
    	//上传照片前的上传数据


        String time = request.getParameter("time");

        if (time.length() < 5) {
            ResponseUtils.renderJson(response, "{\"result\":\"fail\"}");
            return null;
        }
        String location = request.getParameter("location");
        String content  = request.getParameter("content");
        String avatarUrl = request.getParameter("avatarUrl");
        Moments moments = new Moments();
        moments.setContent(content);
        moments.setTime(time);
        moments.setLocation(location);
        moments.setAvatarUrl(avatarUrl);
        try {
        	String directory = uploadService.addContent(moments);
            Moments result = new Moments();
            result.setDirectory(directory);
            ObjectMapper mapper = new ObjectMapper();
            String str = mapper.writeValueAsString(result);
            ResponseUtils.renderJson(response, str);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseUtils.renderJson(response, "{\"result\":\"fail\"}");
        }
        return null;
    }

    /*
     *图片上传
     */
    @RequestMapping("image")
    public String  uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("directory") String directory, HttpServletResponse response)
            throws IllegalStateException, IOException {
        long  startTime = System.currentTimeMillis();
        System.out.println("上传文件" + file.getOriginalFilename());
        if (!file.isEmpty()) {
            try {
                // 文件保存路径
                String filePath = directory + "/" + file.getOriginalFilename();
                // 转存文件
//                String filePath = "E:/" + file.getOriginalFilename();
                file.transferTo(new File(filePath));
                System.out.println("文件名" + filePath);
                ResponseUtils.renderJson(response, "{\"success\": \" " + filePath + "\"}");
            } catch (Exception e) {
                e.printStackTrace();
                ResponseUtils.renderJson(response, "{\"fail\": \" false\"}");
            }
        }

        long  endTime=System.currentTimeMillis();
        System.out.println("方法三的运行时间："+String.valueOf(endTime-startTime)+"ms");

        return null;
    }

}
