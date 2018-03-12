package dao;

import model.human.Notice;

/**
 * Created by liqiao on 2018/1/27.
 */
public interface NoticeMapper {
    Notice queryOne();
    void insertOne(Notice notice);
    void update(Notice notice);
}
