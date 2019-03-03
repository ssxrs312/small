package study.small.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import study.small.entity.Category;
import study.small.entity.Product;
import study.small.entity.Property;
import study.small.service.CategoryService;
import study.small.service.ProductImageService;
import study.small.service.ProductService;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductImageService productImageService;

    @GetMapping("/categories/{cid}/products")
    public PageInfo<Product> list(@PathVariable("cid") int cid,
                                  @RequestParam(value = "start",defaultValue = "1")int start,
                                  @RequestParam(value = "size",defaultValue = "5")int size) throws Exception{
        PageHelper.startPage(start,size,"id desc");

        List<Product> ps = productService.queryByCid(cid);
        productImageService.setFirstProductImage(ps);
        PageInfo<Product> page = new PageInfo<>(ps,5); //5表示导航分页最多有5个，像 [1,2,3,4,5] 这样
        return page;
    }

    @PostMapping("products")
    public Object add(@RequestBody Product product) throws Exception{
        int cid = product.getCategory().getId();
        product.setCid(cid);
        productService.add(product);
        return product;
    }

    @DeleteMapping("products/{id}")
    public String delete(@PathVariable("id") int id) throws Exception{
        productService.delete(id);
        return null;
    }

    @GetMapping("products/{id}")
    public Product get(@PathVariable("id") int id) throws Exception{
        Product p = productService.get(id);
        int cid = p.getCid();
        Category c = categoryService.get(cid);
        p.setCategory(c);
        return p;
    }

    @PutMapping("products")
    public String update(@RequestBody Product product) throws Exception{
        productService.update(product);
        return null;
    }

}
