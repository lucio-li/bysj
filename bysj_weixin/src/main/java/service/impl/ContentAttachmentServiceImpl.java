package service.impl;

import dao.ContentAttachmentMapper;
import model.knowledge.ContentAttachment;
import org.springframework.stereotype.Service;
import service.IContentAttachmentService;

import javax.annotation.Resource;

/**
 * Created by liqiao on 2018/3/4.
 * 附件的service
 */
@Service(value="contentAttachmentService")
public class ContentAttachmentServiceImpl implements IContentAttachmentService{
    @Resource
    private ContentAttachmentMapper contentAttachmentMapper;
    public ContentAttachment queryByContentId(int id) {
        return contentAttachmentMapper.queryByContentId(id);
    }

    public ContentAttachment queryById(int id) {
        return contentAttachmentMapper.queryById(id);
    }

    public void insertOne(ContentAttachment contentAttachment) {
        contentAttachmentMapper.insertOne(contentAttachment);
    }
}
