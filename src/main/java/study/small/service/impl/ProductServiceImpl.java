package study.small.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.small.entity.Category;
import study.small.entity.OrderItem;
import study.small.entity.Product;
import study.small.mapper.ProductMapper;
import study.small.service.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    CategoryService categoryService;

    @Override
    public void add(Product product) {
        productMapper.add(product);
    }

    @Override
    public void delete(int id) {
        productMapper.delete(id);
    }

    @Override
    public void update(Product product) {
        productMapper.update(product);
    }

    @Override
    public Product get(int id) {
        Product p = productMapper.get(id);
        setCategory(p);
        return p;
    }

    @Override
    public List<Product> queryAll() {
        List<Product> ps = productMapper.queryAll();
        setCategory(ps);
        return ps;
    }

    @Override
    public List<Product> queryByCid(int cid) {
        List<Product> ps = productMapper.queryByCid(cid);
        setCategory(ps);
        return ps;
    }

    @Override
    public void fill(Category c) {
        List<Product> ps = queryByCid(c.getId());
        productImageService.setFirstProductImage(ps);
        c.setProducts(ps);
    }

    @Override
    public void fill(List<Category> cs) {
        for(Category c:cs) {
            fill(c);
        }
    }

    @Override
    public void fillByRow(List<Category> cs) {
        int productNumberEachRow = 8;
        for (Category c : cs) {
            List<Product> products =  c.getProducts();
            List<List<Product>> productsByRow =  new ArrayList<>();
            for (int i = 0; i < products.size(); i+=productNumberEachRow) {
                int size = i+productNumberEachRow;
                size= size>products.size()?products.size():size;
                List<Product> productsOfEachRow =products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            c.setProductsByRow(productsByRow);
        }
    }

    @Override
    public void setSaleAndReviewNumber(Product p) {
        int reviewCount = reviewService.getCount(p.getId());
        p.setReviewCount(reviewCount);

        int saleCount = orderItemService.getNumber(p.getId());
        p.setSaleCount(saleCount);
    }

    @Override
    public void setSaleAndReviewNumber(List<Product> ps) {
        for (Product p:ps){
            setSaleAndReviewNumber(p);
        }
    }

    @Override
    public void setCategory(Product p) {
        Category c = categoryService.get(p.getCid());
        p.setCategory(c);
    }

    @Override
    public void setCategory(List<Product> ps) {
        for (Product p:ps){
            setCategory(p);
        }
    }

    @Override
    public List<Product> queryByKeyword(String keyword) {
        List<Product> ps = productMapper.queryByKeyword(keyword);
        productImageService.setFirstProductImage(ps);
        setCategory(ps);
        setSaleAndReviewNumber(ps);
        return ps;
    }


}
