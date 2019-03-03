package study.small.service;

import study.small.entity.Category;
import study.small.entity.Product;

import java.util.List;

public interface ProductService {

    public void add(Product product);

    public void delete(int id);

    public void update(Product product);

    public Product get(int id);

    public List<Product> queryAll();

    public List<Product> queryByCid(int cid);

    public void fill(Category c);

    public void fill(List<Category> cs);

    public void fillByRow(List<Category> cs);

    //set销量和累计评价
    public void setSaleAndReviewNumber(Product p);

    //批量set销量和累计评价
    public void setSaleAndReviewNumber(List<Product> ps);

    public void setCategory(Product p);

    public void setCategory(List<Product> ps);

    public List<Product> queryByKeyword(String keyword);


}
