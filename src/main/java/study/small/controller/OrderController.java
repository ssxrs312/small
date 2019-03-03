package study.small.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import study.small.entity.Category;
import study.small.entity.Order;
import study.small.service.OrderItemService;
import study.small.service.OrderService;
import study.small.util.Result;

import java.util.Date;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;

    @GetMapping("orders")
    public PageInfo<Order> list(@RequestParam(value = "start",defaultValue = "1")int start,
                     @RequestParam(value = "size",defaultValue = "5")int size) throws Exception{

        PageHelper.startPage(start,size,"id desc");
        List<Order> os = orderService.queryAll();
        orderItemService.fill(os);
        PageInfo<Order> page = new PageInfo<>(os,5); //5表示导航分页最多有5个，像 [1,2,3,4,5] 这样
        return page;
    }

    @PutMapping("deliveryOrder/{oid}")
    public Object deliveryOrder(@PathVariable int oid) throws Exception{
        System.out.println(oid);
        Order o = orderService.get(oid);
        o.setDeliveryDate(new Date());
        o.setStatus(OrderService.waitConfirm);
        orderService.update(o);
        return Result.success();
    }


}
