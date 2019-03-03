package study.small.service;

import study.small.entity.Review;
import study.small.entity.User;

import java.util.List;

public interface UserService {

    public void add(User user);

    public void delete(int id);

    public void update(User user);

    public User get(int id);

    public List<User> queryAll();

    public User queryByName(String name);

    public User queryByNameAndPassword(User user);

    public void setUser(Review review);

    public void setUser(List<Review> reviews);


}
