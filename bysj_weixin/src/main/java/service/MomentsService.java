package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.human.Moments;

import java.util.List;

/**
 * 朋友圈动态的接口服务
 * Created by lq on 2017/12/13.
 */
public interface MomentsService {
    List<Moments> queryAll() throws JsonProcessingException;
    String deleteOne(String time);
}
