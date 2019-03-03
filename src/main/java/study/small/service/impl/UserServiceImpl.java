package study.small.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.small.entity.Review;
import study.small.entity.User;
import study.small.mapper.UserMapper;
import study.small.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public void add(User user) {
        userMapper.add(user);
    }

    @Override
    public void delete(int id) {
        userMapper.delete(id);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public User get(int id) {
        User user = userMapper.get(id);
        return user;
    }

    @Override
    public List<User> queryAll() {
        List<User> users = userMapper.queryAll();
        return users;
    }

    @Override
    public User queryByName(String name) {
        User user = userMapper.queryByName(name);
        return user;
    }

    @Override
    public User queryByNameAndPassword(User user) {
        User u = userMapper.queryByNameAndPassword(user);
        return u;
    }

    @Override
    public void setUser(Review review) {
        User user = userMapper.get(review.getUid());
        review.setUser(user);
    }

    @Override
    public void setUser(List<Review> reviews) {
        for(Review review:reviews) {
            setUser(review);
        }
    }
}
