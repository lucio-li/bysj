package service;

import model.knowledge.ApplyUser;

/**
 * Created by liqiao on 2018/1/28.
 * 申请入库的申请人的service
 */
public interface IApplyUserService {

    int insertOne(ApplyUser user);
    ApplyUser queryById(int id);
}
