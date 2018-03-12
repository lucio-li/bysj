package dao;

import model.knowledge.ContentAttachment;

/**
 * Created by liqiao on 2018/1/27.
 */
public interface ContentAttachmentMapper {
    ContentAttachment queryByContentId(int id);
    ContentAttachment queryById(int id);
    void insertOne(ContentAttachment contentAttachment);
}
