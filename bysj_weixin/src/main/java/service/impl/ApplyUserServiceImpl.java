package service.impl;

import dao.ApplyUserMapper;
import model.knowledge.ApplyUser;
import org.springframework.stereotype.Service;
import service.IApplyUserService;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by liqiao on 2018/3/4.
 * 附件的service
 */
@Service(value="applyUserService")
public class ApplyUserServiceImpl implements IApplyUserService {
    @Resource
    private ApplyUserMapper applyUserMapper;

    public int insertOne(ApplyUser user) {
        if ((user.getUsername() == null || "".equals(user.getUsername())) && (user.getPhone() == null || "".equals(user.getPhone())) && (user.getEmail() == null || "".equals(user.getEmail()))) {
            return 0;//申请人信息为空，不插入数据库
        }
        Date date = new Date();
        user.setCreateTime(date);
        applyUserMapper.insertOne(user);
        return applyUserMapper.queryIdByTime(date);
    }

    public ApplyUser queryById(int id) {
        return applyUserMapper.queryById(id);
    }
}
