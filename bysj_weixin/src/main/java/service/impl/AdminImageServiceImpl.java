package service.impl;

import com.alibaba.fastjson.JSONObject;
import dao.ImageMapper;
import model.message.Image;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service.IAdminImageService;
import utils.AppConfig;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * Created by liqiao on 2018/1/27.
 * 后台网站对菜单的管理的service
 */
@Service(value="adminImageService")
public class AdminImageServiceImpl implements IAdminImageService{

    @Resource
    private ImageMapper imageMapper;

    //添加图片素材
    public void addOne(MultipartFile multipartFile, HttpServletResponse response) {
        File file = null;
        if (!multipartFile.isEmpty()) {
            try {
                // 文件保存路径
                String filePath = "./" + multipartFile.getOriginalFilename();
                // 转存文件
//                String filePath = "E:/" + file.getOriginalFilename();
                multipartFile.transferTo(new File(filePath));
                file = new File(filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();
        String uploadurl = String.format("https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=%s&type=%s",
                AppConfig.access_token, "image");
        PostMethod post = new PostMethod(uploadurl);
        post.setRequestHeader(
                "User-Agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:30.0) Gecko/20100101 Firefox/30.0");
        post.setRequestHeader("Host", "file.api.weixin.qq.com");
        post.setRequestHeader("Connection", "Keep-Alive");
        post.setRequestHeader("Cache-Control", "no-cache");
        String media_id = null;
        String url = null;
        try {
            if (file != null) {
                FilePart filepart = new FilePart("media", file, "image/jpeg",
                        "UTF-8");
                Part[] parts = new Part[]{filepart};
                MultipartRequestEntity entity = new MultipartRequestEntity(
                        parts,

                        post.getParams());
                post.setRequestEntity(entity);
                int status = client.executeMethod(post);
                if (status == HttpStatus.SC_OK) {
                    String responseContent = post.getResponseBodyAsString();
                    JSONObject json = JSONObject.parseObject(responseContent);// 初始化解析json格式的对象

                    if (json.get("errcode") == null){ // 上传成功  {"type":"TYPE","media_id":"MEDIA_ID","created_at":123456789}
                        //添加图片成功后把数据插入数据库
                        media_id = json.get("media_id").toString();
                        url = json.get("url").toString();
                        String imageName = file.getName();
                        Image image = new Image();
                        image.setMedia_id(media_id);
                        image.setUrl(url);
                        image.setType("image");
                        image.setName(imageName);
                        String key = System.currentTimeMillis() + "";
                        image.setKey(key);
                        imageMapper.addOne(image);
                        file.delete();//添加完删除文件
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(media_id);
        }

    }

    public List<Image> queryAll() {
        return imageMapper.queryAll();
    }

    public void deleteById(String id) {
        imageMapper.deleteById(Integer.parseInt(id));
    }


}
