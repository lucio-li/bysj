package dao;

import model.Comments;

public interface CommentsMapper {

    void insertOne(Comments comments);
    int deleteById(int id);
}
