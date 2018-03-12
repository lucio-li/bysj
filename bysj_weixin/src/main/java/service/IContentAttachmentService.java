package service;

import model.knowledge.ContentAttachment;

/**
 * Created by liqiao on 2018/1/28.
 * 知识库的具体内容的service
 */
public interface IContentAttachmentService {



    ContentAttachment queryByContentId(int id);
    ContentAttachment queryById(int id);
    void insertOne(ContentAttachment contentAttachment);
}
