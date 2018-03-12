package service.impl;

import dao.ContentApplyMapper;
import model.knowledge.Content;
import org.springframework.stereotype.Service;
import service.IContentApplyService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liqiao on 2018/3/4.
 * 附件的service
 */
@Service(value="contentApplyService")
public class ContentApplyServiceImpl implements IContentApplyService {
    @Resource
    private ContentApplyMapper contentApplyMapper;

    public void insertOne(Content content) {
        contentApplyMapper.insertOne(content);
    }

    public List<Content> queryAll() {

        return contentApplyMapper.queryAll();
    }

    public Content queryById(int id) {
        return contentApplyMapper.queryById(id);
    }

    public void deleleById(int id) {
        contentApplyMapper.deleleById(id);
    }
}
