package study.small.service;

import study.small.entity.Category;

import java.util.List;

public interface CategoryService {

    public void add(Category category);

    public void delete(int id);

    public void update(Category category);

    public Category get(int id);

    public List<Category> queryAll();

    public void removeCategoryFromProduct(Category c);

    public void removeCategoryFromProduct(List<Category> cs);

}
