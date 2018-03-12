package dao;

import model.knowledge.ApplyUser;

import java.util.Date;

/**
 * Created by liqiao on 2018/1/27.
 */
public interface ApplyUserMapper {
    void insertOne(ApplyUser applyUser);
    int queryIdByTime(Date date);
    ApplyUser queryById(int id);
}
