package study.small.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import study.small.entity.Product;
import study.small.entity.PropertyValue;
import study.small.service.ProductService;
import study.small.service.PropertyValueService;

import java.util.List;

@RestController
public class PropertyValueController {

    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    ProductService productService;

    @GetMapping("products/{pid}/propertyValues")
    public List<PropertyValue> list(@PathVariable("pid") int pid) throws Exception{
        Product product = productService.get(pid);
        propertyValueService.init(product);
        List<PropertyValue> pvs = propertyValueService.queryByPid(product.getId());

        return pvs;
    }

    @PutMapping("propertyValues")
    public Object update(@RequestBody PropertyValue propertyValue) throws Exception{
//        System.out.println(propertyValue);
        propertyValueService.update(propertyValue);
        return propertyValue;
    }
}
