package study.small.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import study.small.entity.Category;
import study.small.service.CategoryService;
import study.small.util.ImageUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories") //获取queryAll，get
    public PageInfo<Category> list(@RequestParam(value = "start",defaultValue = "1")int start,
                                   @RequestParam(value = "size",defaultValue = "5")int size) throws Exception{
        PageHelper.startPage(start,size,"id desc");
        List<Category> cs = categoryService.queryAll();
        PageInfo<Category> page = new PageInfo<>(cs,5); //5表示导航分页最多有5个，像 [1,2,3,4,5] 这样
        return page;
    }

    @PostMapping("/categories")
    public Object add(Category category, MultipartFile image, HttpServletRequest request) throws Exception{
        categoryService.add(category);
        saveOrUpdateImageFile(category, image, request);
        return category;
    }
    public void saveOrUpdateImageFile(Category category, MultipartFile image, HttpServletRequest request) throws Exception{
        File imageFolder= new File(request.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,category.getId()+".jpg");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        image.transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
    }

    @DeleteMapping("/categories/{id}")
    public String delete(@PathVariable("id") int id, HttpServletRequest request) throws Exception{
        categoryService.delete(id);
        File  imageFolder= new File(request.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,id+".jpg");
        file.delete();
        return null;
    }

    @GetMapping("categories/{id}")
    public Category get(@PathVariable("id") int id) throws Exception{
        Category c = categoryService.get(id);
        return c;
    }

    @PutMapping("categories/{id}")
    public Category update(Category category, MultipartFile image,HttpServletRequest request) throws Exception{
        categoryService.update(category);

        if(image!=null) {
            saveOrUpdateImageFile(category, image, request);
        }
        return category;
    }


}
