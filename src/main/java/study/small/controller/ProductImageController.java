package study.small.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import study.small.entity.Category;
import study.small.entity.Product;
import study.small.entity.ProductImage;
import study.small.service.ProductImageService;
import study.small.service.ProductService;
import study.small.util.ImageUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class ProductImageController {

    @Autowired
    ProductImageService productImageService;
    @Autowired
    ProductService productService;

    @GetMapping("products/{pid}/productImages")
    public PageInfo<ProductImage> list(@PathVariable("pid") int pid, @RequestParam String type) throws Exception{
        Product p = productService.get(pid);

        if (productImageService.type_single.equals(type)){
            PageHelper.orderBy("id desc");
            List<ProductImage> pis = productImageService.queryByPidAndType(pid, type);
            PageInfo<ProductImage> page_singles = new PageInfo<>(pis); //5表示导航分页最多有5个，像 [1,2,3,4,5] 这样
            return page_singles;
        }else if (productImageService.type_detail.equals(type)){
            PageHelper.orderBy("id desc");
            List<ProductImage> pis = productImageService.queryByPidAndType(pid, type);
            PageInfo<ProductImage> page_details = new PageInfo<>(pis); //5表示导航分页最多有5个，像 [1,2,3,4,5] 这样
            return page_details;
        }else {
            return new PageInfo<>();
        }
    }

    @PostMapping("productImages")
    public String add(ProductImage productImage,
                    MultipartFile image, HttpServletRequest request) throws Exception{

        Product product = productService.get(productImage.getPid());
        productImage.setProduct(product);
        productImageService.add(productImage);

        saveOrUpdateImageFile(productImage,image,request);
        return null;
    }
    public void saveOrUpdateImageFile(ProductImage productImage, MultipartFile image, HttpServletRequest request) throws Exception{
        String folder = "img/";
        if(ProductImageService.type_single.equals(productImage.getType())){
            folder +="productSingle";
        }
        else{
            folder +="productDetail";
        }
        File  imageFolder= new File(request.getServletContext().getRealPath(folder));
        File file = new File(imageFolder,productImage.getId()+".jpg");
//        System.out.println(file);
        String fileName = file.getName();
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        try {
            image.transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(ProductImageService.type_single.equals(productImage.getType())){
            String imageFolder_small= request.getServletContext().getRealPath("img/productSingle_small");
            String imageFolder_middle= request.getServletContext().getRealPath("img/productSingle_middle");
            File f_small = new File(imageFolder_small, fileName);
            File f_middle = new File(imageFolder_middle, fileName);
            f_small.getParentFile().mkdirs();
            f_middle.getParentFile().mkdirs();
            ImageUtil.resizeImage(file, 56, 56, f_small);
            ImageUtil.resizeImage(file, 217, 190, f_middle);
        }
    }

    @DeleteMapping("productImages/{id}")
    public String delete(@PathVariable("id") int id,HttpServletRequest request) throws Exception{
        ProductImage productImage = productImageService.get(id);
        productImageService.delete(id);
        deleteImageFile(productImage,request);
        return null;
    }
    public void deleteImageFile(ProductImage productImage,HttpServletRequest request) {
        String folder = "img/";
        if(ProductImageService.type_single.equals(productImage.getType()))
            folder +="productSingle";
        else
            folder +="productDetail";

        File  imageFolder= new File(request.getServletContext().getRealPath(folder));
        File file = new File(imageFolder,productImage.getId()+".jpg");
        String fileName = file.getName();
        file.delete();
        if(ProductImageService.type_single.equals(productImage.getType())){
            String imageFolder_small= request.getServletContext().getRealPath("img/productSingle_small");
            String imageFolder_middle= request.getServletContext().getRealPath("img/productSingle_middle");
            File f_small = new File(imageFolder_small, fileName);
            File f_middle = new File(imageFolder_middle, fileName);
            f_small.delete();
            f_middle.delete();
        }
    }

}
