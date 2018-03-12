package service;

import model.human.Notice;

/**
 * Created by liqiao on 2018/1/28.
 * 知识库的具体内容的service
 */
public interface INoticeService {
    Notice queryOne();
    void insertOne(Notice notice);
    void update(Notice notice);
}
