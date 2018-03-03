package dao;

import model.message.Image;

import java.util.List;

/**
 * Created by liqiao on 2018/1/27.
 */
public interface ImageMapper {
    void addOne(Image image);

    /**
     * 查询所有的图片信息
     */
    List<Image> queryAll();
    void deleteById(int id);
    Image queryByKey(String key);
}
