package study.small.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.small.entity.Product;
import study.small.entity.ProductImage;
import study.small.mapper.ProductImageMapper;
import study.small.service.ProductImageService;

import java.util.List;

@Service
public class ProductImageImpl implements ProductImageService {

    @Autowired
    ProductImageMapper productImageMapper;

    @Override
    public void add(ProductImage productImage) {
        productImageMapper.add(productImage);
    }

    @Override
    public void delete(int id) {
        productImageMapper.delete(id);
    }

    @Override
    public void update(ProductImage productImage) {
        productImageMapper.update(productImage);
    }

    @Override
    public ProductImage get(int id) {
        ProductImage pi = productImageMapper.get(id);
        return pi;
    }

    @Override
    public List<ProductImage> queryAll() {
        List<ProductImage> pis = productImageMapper.queryAll();
        return pis;
    }

    @Override
    public List<ProductImage> queryByPidAndType(int pid, String type) {
        ProductImage productImage = new ProductImage();
        productImage.setPid(pid);
        productImage.setType(type);
        List<ProductImage> pis = productImageMapper.queryByPidAndType(productImage);
        return pis;
    }

    @Override
    public void setFirstProductImage(Product p) {
        List<ProductImage> pis_single = queryByPidAndType(p.getId(), type_single);
        if (!pis_single.isEmpty()){
            ProductImage productImage = pis_single.get(0);// 获取集合中的第一张图片
            p.setFirstProductImage(productImage);
        }
    }

    @Override
    public void setFirstProductImage(List<Product> ps) {
        for (Product p:ps){
            setFirstProductImage(p);
        }
    }



}
