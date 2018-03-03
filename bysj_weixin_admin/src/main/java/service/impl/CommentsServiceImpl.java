package service.impl;

import dao.CommentsMapper;
import model.Comments;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import service.CommentsService;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by lq on 2017/12/13.
 */
@Service(value="commentsService")
public class CommentsServiceImpl implements CommentsService {
    @Resource
    private CommentsMapper commentsMapper;
    private Logger logger = Logger.getLogger(CommentsServiceImpl.class);


    public String addOne(Comments comments) {
        Date now = new Date();
        comments.setCreateTime(now);
        try{
            commentsMapper.insertOne(comments);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }


    }

    public String deleteOne(int id) {
        logger.info("测试log");
        int a = commentsMapper.deleteById(id);
        if (a == 0) {
            return "fail";//删除失败
        } else {
            return "success";//删除成功
        }


    }
}
