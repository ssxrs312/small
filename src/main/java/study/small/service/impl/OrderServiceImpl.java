package study.small.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import study.small.entity.Order;
import study.small.entity.OrderItem;
import study.small.entity.User;
import study.small.mapper.OrderMapper;
import study.small.service.OrderItemService;
import study.small.service.OrderService;
import study.small.service.UserService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserService userService;
    @Autowired
    OrderItemService orderItemService;

    @Override
    public void add(Order order) {
        orderMapper.add(order);
    }

    @Override
    public void delete(int id) {
        orderMapper.delete(id);
    }

    @Override
    public void update(Order order) {
        orderMapper.update(order);
    }

    @Override
    public Order get(int id) {
        Order order = orderMapper.get(id);
        setUser(order);
        return order;
    }

    @Override
    public List<Order> queryAll() {
        List<Order> os = orderMapper.queryAll();
        setUser(os);
        return os;
    }

    @Override
    public void setUser(Order o) {
        int uid = o.getUid();
        User user = userService.get(uid);
        o.setUser(user);
    }

    @Override
    public void setUser(List<Order> os) {
        for (Order o:os){
            setUser(o);
        }
    }

    @Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
    @Override
    public float add(Order order, List<OrderItem> ois) {
        float total = 0;
        add(order);

        if(false)
            throw new RuntimeException();

        for (OrderItem oi: ois) {
            oi.setOid(order.getId());
            orderItemService.update(oi);
            total+=oi.getProduct().getPromotePrice()*oi.getNumber();
        }
        return total;
    }

    @Override
    public List<Order> queryOrderByUid(int uid, String status) {
        Order order = new Order();
        order.setUid(uid);
        order.setStatus(status);
        List<Order> os = orderMapper.queryOrderByUid(order);
        return os;
    }
}
