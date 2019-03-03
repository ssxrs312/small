package study.small.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import study.small.entity.Category;
import study.small.entity.Property;
import study.small.service.CategoryService;
import study.small.service.PropertyService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
public class PropertyController {

    @Autowired
    PropertyService propertyService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories/{cid}/properties")
    public PageInfo<Property> list(@PathVariable("cid") int cid,
                                   @RequestParam(value = "start",defaultValue = "1")int start,
                                   @RequestParam(value = "size",defaultValue = "5")int size) throws Exception{
        PageHelper.startPage(start,size,"id desc");
        List<Property> ps = propertyService.queryByCid(cid);
        PageInfo<Property> page = new PageInfo<>(ps,5); //5表示导航分页最多有5个，像 [1,2,3,4,5] 这样
        return page;
    }

    @PostMapping("properties")
    public Object add(@RequestBody Property property) throws Exception{
        int cid = property.getCategory().getId();
        property.setCid(cid);
        propertyService.add(property);
        return property;
    }

    @DeleteMapping("properties/{id}")
    public String delete(@PathVariable("id") int id) throws Exception{
        propertyService.delete(id);
        return null;
    }

    @GetMapping("properties/{id}")
    public Property get(@PathVariable("id") int id) throws Exception{
        Property p = propertyService.get(id);
        int cid = p.getCid();
        Category c = categoryService.get(cid);
        p.setCategory(c);
        return p;
    }

    @PutMapping("properties/{id}")
    public String update(@RequestBody Property property) throws Exception{
        propertyService.update(property);
        return null;
    }





}
