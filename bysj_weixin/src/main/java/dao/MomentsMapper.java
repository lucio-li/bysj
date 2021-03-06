package dao;


import model.human.Moments;

import java.util.List;

public interface MomentsMapper {
	List<Moments> queryAll();
    void insertOne(Moments moments);
    List<String> queryAvatarUrlList();
    int deleteOne(String time);
}
