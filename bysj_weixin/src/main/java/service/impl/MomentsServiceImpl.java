package service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.MomentsMapper;
import model.human.Moments;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import service.MomentsService;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lq on 2017/12/13.
 */
@Service(value="momentsService")
public class MomentsServiceImpl implements MomentsService {
    @Resource
    private MomentsMapper momentsMapper;
    private Logger logger = Logger.getLogger(MomentsServiceImpl.class);
    public List<Moments> queryAll() throws JsonProcessingException {
        List<Moments> momentsList = momentsMapper.queryAll();
        for (int i = 0, len = momentsList.size(); i < len; i++) {
            Moments moments = momentsList.get(i);
            String directoryPath = moments.getDirectory();
            String imageUrl = "../upload/getImg?file=";//文件夹名字
            System.out.println(moments.numberDataTime());
            File directory = new File(directoryPath);
            String filename = "";
            List<String> imageUrlList = new ArrayList<String>();
            if (directory.exists()) {
                File file[] = directory.listFiles();
                for (int j = 0; j < file.length; j++) {
                    filename = imageUrl + directoryPath + "/" + file[j].getName();
                    imageUrlList.add(filename);
                }
            }
            moments.setImageUrlLlist(imageUrlList);
        }
        ObjectMapper mapper = new ObjectMapper();
        String momentsJson = mapper.writeValueAsString(momentsList);

        return momentsList;

    }

    public String deleteOne(String time) {
        logger.info("测试log");
        int a = momentsMapper.deleteOne(time);
        if (a == 0) {
            return "fail";//删除失败
        } else {
            return "success";//删除成功
        }


    }
}
