package dao;


import model.human.Comments;

public interface CommentsMapper {

    void insertOne(Comments comments);
    int deleteById(int id);
}
