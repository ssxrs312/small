package study.small.service;

import study.small.entity.Product;
import study.small.entity.ProductImage;

import java.util.List;

public interface ProductImageService {

    String type_single = "single";
    String type_detail = "detail";

    public void add(ProductImage productImage);

    public void delete(int id);

    public void update(ProductImage productImage);

    public ProductImage get(int id);

    public List<ProductImage> queryAll();

    public List<ProductImage> queryByPidAndType(int pid,String type);

    public void setFirstProductImage(Product p);

    public void setFirstProductImage(List<Product> ps);


}
