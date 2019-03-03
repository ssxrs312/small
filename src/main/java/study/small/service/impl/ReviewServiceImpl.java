package study.small.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.small.entity.Review;
import study.small.mapper.ReviewMapper;
import study.small.service.ReviewService;
import study.small.service.UserService;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewMapper reviewMapper;
    @Autowired
    UserService userService;

    @Override
    public void add(Review review) {
        reviewMapper.add(review);
    }

    @Override
    public void delete(int id) {
        reviewMapper.delete(id);
    }

    @Override
    public void update(Review review) {
        reviewMapper.update(review);
    }

    @Override
    public Review get(int id) {
        Review r = reviewMapper.get(id);
        userService.setUser(r);
        return r;
    }

    @Override
    public List<Review> queryAll() {
        List<Review> rs = reviewMapper.queryAll();
        userService.setUser(rs);
        return rs;
    }

    @Override
    public List<Review> queryByPid(int pid) {
        List<Review> rs = reviewMapper.queryByPid(pid);
        userService.setUser(rs);
        return rs;
    }

    @Override
    public int getCount(int pid) {
        return queryByPid(pid).size();
    }
}
