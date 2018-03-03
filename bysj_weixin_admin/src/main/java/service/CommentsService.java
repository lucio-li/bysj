package service;

import model.Comments;

/**
 * 朋友圈动态的接口服务
 * Created by lq on 2017/12/13.
 */
public interface CommentsService {
    String addOne(Comments comments);
    String deleteOne(int id);
}
