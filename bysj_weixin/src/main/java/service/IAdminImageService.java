package service;

import model.message.Image;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by liqiao on 2018/1/28.
 * 后台网站的图片的service
 */
public interface IAdminImageService {
    /**
     * 增加图片素材
     */
    void addOne(MultipartFile multipartFile, HttpServletResponse response);

    /**
     * 查询所有的图片
     */
    List<Image> queryAll();

    /**
     * 删除图片
     */
    void deleteById(String id);

}
