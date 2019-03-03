package study.small.service;

import study.small.entity.Order;
import study.small.entity.OrderItem;

import java.util.List;

public interface OrderItemService {

    public void add(OrderItem orderItem);

    public void delete(int id);

    public void update(OrderItem orderItem);

    public OrderItem get(int id);

    public List<OrderItem> queryAll();

    public void fill(Order o);

    public void fill(List<Order> os);

    public List<OrderItem> queryByOid(int oid);

    public void setProduct(OrderItem orderItem);

    public void setProduct(List<OrderItem> orderItems);

    public List<OrderItem> queryByPid(int pid);

    public int getNumber(int pid);

    public List<OrderItem> queryByUidAndOrderIsNull(int uid);

}
