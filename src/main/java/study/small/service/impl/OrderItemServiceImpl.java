package study.small.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.small.entity.Order;
import study.small.entity.OrderItem;
import study.small.entity.Product;
import study.small.entity.ProductImage;
import study.small.mapper.OrderItemMapper;
import study.small.mapper.OrderMapper;
import study.small.service.OrderItemService;
import study.small.service.OrderService;
import study.small.service.ProductImageService;
import study.small.service.ProductService;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    ProductService productService;
    @Autowired
    ProductImageService productImageService;


    @Override
    public void add(OrderItem orderItem) {
        orderItemMapper.add(orderItem);
    }

    @Override
    public void delete(int id) {
        orderItemMapper.delete(id);
    }

    @Override
    public void update(OrderItem orderItem) {
        orderItemMapper.update(orderItem);
    }

    @Override
    public OrderItem get(int id) {
        OrderItem oi = orderItemMapper.get(id);
        setProduct(oi);
        return oi;
    }

    @Override
    public List<OrderItem> queryAll() {
        List<OrderItem> ois = orderItemMapper.queryAll();
        return ois;
    }

    @Override
    public void fill(Order o) {
        List<OrderItem> ois = queryByOid(o.getId());
        setProduct(ois);
        float total = 0;
        int totalNumber = 0;
        for (OrderItem oi : ois) {
            total+=oi.getNumber()*oi.getProduct().getPromotePrice();
            totalNumber+=oi.getNumber();
        }
        o.setTotal(total);
        o.setTotalNumber(totalNumber);
        o.setOrderItems(ois);
    }

    @Override
    public void fill(List<Order> os) {
        for (Order o:os){
            fill(o);
        }
    }

    @Override
    public List<OrderItem> queryByOid(int oid) {
        List<OrderItem> ois = orderItemMapper.queryByOid(oid);
        return ois;
    }

    @Override
    public void setProduct(OrderItem orderItem) {
        Product p = productService.get(orderItem.getPid());
        productImageService.setFirstProductImage(p);
        orderItem.setProduct(p);
    }

    @Override
    public void setProduct(List<OrderItem> orderItems) {
        for (OrderItem oi:orderItems){
            setProduct(oi);
        }
    }

    @Override
    public List<OrderItem> queryByPid(int pid) {
        List<OrderItem> ois = orderItemMapper.queryByPid(pid);
        return ois;
    }

    @Override
    public int getNumber(int pid) {
        List<OrderItem> ois = queryByPid(pid);
        int result = 0;
        for (OrderItem oi:ois){
            result += oi.getNumber();
        }
        return result;
    }

    @Override
    public List<OrderItem> queryByUidAndOrderIsNull(int uid) {
        List<OrderItem> ois = orderItemMapper.queryByUidAndOrderIsNull(uid);
        setProduct(ois);
        return ois;
    }

}
