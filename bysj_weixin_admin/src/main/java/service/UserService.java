package service;

import model.User;

import java.util.List;

/**
 * Created by lq on 2017/12/10.
 */
public interface UserService {
    List<User> queryAll();
    void insertOne(User user);
    void updateOne(User user);
    void deleteOne(User user);
    User queryOne(String openid);
}
