package service.impl;

import dao.UserMapper;
import model.User;
import org.springframework.stereotype.Service;
import service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lq on 2017/12/10.
 */
@Service(value="userService")
public class UserServiceImpl implements UserService{
    @Resource
    private UserMapper userMapper;
    public List<User> queryAll() {
        List<User> userList = null;
        userList = userMapper.selectAll();
        return userList;
    }

    public void insertOne(User user) {
    	User userInDB;
    	userInDB = userMapper.queryOne(user.getOpenid());
    	if (userInDB == null) {
    		userMapper.insertOne(user);
    	}
        
    }
    public void updateOne(User user) {
        User userInDB;
        userInDB = userMapper.queryOne(user.getOpenid());
        if (userInDB == null) {
            userMapper.insertOne(user);
        } else {
            userMapper.updateOne(user);
        }

    }
    public void deleteOne(User user) {
        User userInDB;
        userInDB = userMapper.queryOne(user.getOpenid());
        System.out.println("openid" + user.getOpenid());
        if (userInDB != null) {
            userMapper.deleteOne(user);
        }

    }
	public User queryOne(String openid) {
		// TODO Auto-generated method stub
		return (User)userMapper.queryOne(openid);
		
	}
}
