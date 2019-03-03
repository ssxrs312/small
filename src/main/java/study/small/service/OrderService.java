package study.small.service;

import study.small.entity.Order;
import study.small.entity.OrderItem;

import java.util.List;

public interface OrderService {

    String waitPay = "waitPay";                //待付款
    String waitDelivery = "waitDelivery";     //待发货
    String waitConfirm = "waitConfirm";      //待收货
    String waitReview = "waitReview";       //等评价
    String finish = "finish";              //完成
    String delete = "delete";             //刪除

    public void add(Order order);

    public void delete(int id);

    public void update(Order order);

    public Order get(int id);

    public List<Order> queryAll();

    public void setUser(Order o);

    public void setUser(List<Order> os);

    public float add(Order order, List<OrderItem> ois);

    //根据uid查询订单数据集合
    List<Order> queryOrderByUid(int uid,String status);

}
