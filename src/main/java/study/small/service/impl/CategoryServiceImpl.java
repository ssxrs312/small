package study.small.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.small.entity.Category;
import study.small.entity.Product;
import study.small.mapper.CategoryMapper;
import study.small.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {
        categoryMapper.add(category);
    }

    @Override
    public void delete(int id) {
        categoryMapper.delete(id);
    }

    @Override
    public void update(Category category) {
        categoryMapper.update(category);
    }

    @Override
    public Category get(int id) {
        Category c = categoryMapper.get(id);
        return c;
    }

    @Override
    public List<Category> queryAll() {
        List<Category> cs = categoryMapper.queryAll();
        return cs;
    }

    @Override
    public void removeCategoryFromProduct(Category c) {
        List<Product> ps = c.getProducts();
        if (null!=ps){
            for(Product p:ps){
                p.setCategory(null);
            }
        }
    }

    @Override
    public void removeCategoryFromProduct(List<Category> cs) {
        for (Category c:cs){
            removeCategoryFromProduct(c);
        }
    }
}
