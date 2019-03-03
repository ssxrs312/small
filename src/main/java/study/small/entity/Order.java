package study.small.entity;

import study.small.service.OrderService;

import java.util.Date;
import java.util.List;

public class Order {
    //orderCode,address,post,receiver,mobile,userMessage,createDate,payDate,deliveryDate,confirmDate,uid,status
    private int id;
    private String orderCode;
    private String address;
    private String post;
    private String receiver;
    private String mobile;
    private String userMessage;
    private Date createDate;
    private Date payDate;
    private Date deliveryDate;
    private Date confirmDate;
    private int uid;
    private String status;

    //非数据库字段
    private User user;                    //该订单对应的用户
    private List<OrderItem> orderItems;  //订单下包含的订单项
    private Float total;                //订单总金额
    private int totalNumber;           //订单的总商品数量
    private String statusDesc;        //订单状态




    public Order() {
    }

    public Order(int id, String orderCode, String address, String post, String receiver, String mobile, String userMessage, Date createDate, Date payDate, Date deliveryDate, Date confirmDate, int uid, String status) {
        this.id = id;
        this.orderCode = orderCode;
        this.address = address;
        this.post = post;
        this.receiver = receiver;
        this.mobile = mobile;
        this.userMessage = userMessage;
        this.createDate = createDate;
        this.payDate = payDate;
        this.deliveryDate = deliveryDate;
        this.confirmDate = confirmDate;
        this.uid = uid;
        this.status = status;
    }

    public Order(int id, String orderCode, String address, String post, String receiver, String mobile, String userMessage, Date createDate, Date payDate, Date deliveryDate, Date confirmDate, int uid, String status, User user, List<OrderItem> orderItems, Float total, int totalNumber, String statusDesc) {
        this.id = id;
        this.orderCode = orderCode;
        this.address = address;
        this.post = post;
        this.receiver = receiver;
        this.mobile = mobile;
        this.userMessage = userMessage;
        this.createDate = createDate;
        this.payDate = payDate;
        this.deliveryDate = deliveryDate;
        this.confirmDate = confirmDate;
        this.uid = uid;
        this.status = status;
        this.user = user;
        this.orderItems = orderItems;
        this.total = total;
        this.totalNumber = totalNumber;
        this.statusDesc = statusDesc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getStatusDesc() {
        if(null!=statusDesc)
            return statusDesc;
        String desc ="未知";
        switch(status){
            case OrderService.waitPay:
                desc="待付";
                break;
            case OrderService.waitDelivery:
                desc="待发";
                break;
            case OrderService.waitConfirm:
                desc="待收";
                break;
            case OrderService.waitReview:
                desc="等评";
                break;
            case OrderService.finish:
                desc="完成";
                break;
            case OrderService.delete:
                desc="刪除";
                break;
            default:
                desc="未知";
        }
        statusDesc = desc;
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderCode='" + orderCode + '\'' +
                ", address='" + address + '\'' +
                ", post='" + post + '\'' +
                ", receiver='" + receiver + '\'' +
                ", mobile='" + mobile + '\'' +
                ", userMessage='" + userMessage + '\'' +
                ", createDate=" + createDate +
                ", payDate=" + payDate +
                ", deliveryDate=" + deliveryDate +
                ", confirmDate=" + confirmDate +
                ", uid=" + uid +
                ", status='" + status + '\'' +
                ", user=" + user +
                ", orderItems=" + orderItems +
                ", total=" + total +
                ", totalNumber=" + totalNumber +
                ", statusDesc='" + statusDesc + '\'' +
                '}';
    }
}
