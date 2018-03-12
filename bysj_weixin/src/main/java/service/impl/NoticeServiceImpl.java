package service.impl;

import dao.NoticeMapper;
import model.human.Notice;
import org.springframework.stereotype.Service;
import service.INoticeService;

import javax.annotation.Resource;

/**
 * Created by liqiao on 2018/3/4.
 * 历史申请的service
 */
@Service(value="noticeService")
public class NoticeServiceImpl implements INoticeService {
    @Resource
    private NoticeMapper noticeMapper;

    public Notice queryOne() {
        return noticeMapper.queryOne();
    }
    public void insertOne(Notice notice) {
        noticeMapper.insertOne(notice);
    }
    public void update(Notice notice) {
        noticeMapper.update(notice);
    }
}
