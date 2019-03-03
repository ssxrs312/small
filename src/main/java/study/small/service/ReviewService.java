package study.small.service;

import study.small.entity.Review;

import java.util.List;

public interface ReviewService {

    public void add(Review review);

    public void delete(int id);

    public void update(Review review);

    public Review get(int id);

    public List<Review> queryAll();

    public List<Review> queryByPid(int pid);

    //根据pid查询产品的所有销量，
    public int getCount(int pid);
}
