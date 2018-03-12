package service.impl;

import dao.HistoryApplyMapper;
import model.knowledge.Content;
import org.springframework.stereotype.Service;
import service.IHistoryApplyService;

import javax.annotation.Resource;

/**
 * Created by liqiao on 2018/3/4.
 * 历史申请的service
 */
@Service(value="historyApplyService")
public class HistoryApplyServiceImpl implements IHistoryApplyService {
    @Resource
    private HistoryApplyMapper historyApplyMapper;

    public void insertPass(Content content) {
        historyApplyMapper.insertPass(content);
    }
    public void insertRefuse(Content content) {
        historyApplyMapper.insertRefuse(content);
    }

}
