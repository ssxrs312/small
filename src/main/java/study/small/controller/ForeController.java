package study.small.controller;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import study.small.comparator.*;
import study.small.entity.*;
import study.small.service.*;
import study.small.util.Result;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class ForeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    PropertyService propertyService;
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrderService orderService;

    //首页
    @GetMapping("forehome")
    public List<Category> home() throws Exception{
        List<Category> cs = categoryService.queryAll();
        productService.fill(cs);
        productService.fillByRow(cs);
//        categoryService.removeCategoryFromProduct(cs);
        return cs;
    }

    //注册
    @PostMapping("foreregister")
    public Object register(@RequestBody User user) throws Exception{
        User u = userService.queryByName(user.getName());
        if (null!=u){
            String message ="用户已注册,请重新注册";
            return Result.fail(message);
        }
        userService.add(user);
        return Result.success();
    }

    //登录
    @PostMapping("forelogin")
    public Object login(@RequestBody User user, HttpSession session) throws Exception{
        User u = userService.queryByNameAndPassword(user);
        if (null==u){
            String message = "账号密码错误";
            return Result.fail(message);
        }
        session.setAttribute("u", u);
//        System.out.println("u");
        return Result.success();
    }

    //产品页
    @GetMapping("foreproduct/{pid}")
    public Object product(@PathVariable("pid") int pid) throws Exception{
        Product product = productService.get(pid);
        List<ProductImage> pis_single = productImageService.queryByPidAndType(pid, ProductImageService.type_single);
        List<ProductImage> pis_detail = productImageService.queryByPidAndType(pid, ProductImageService.type_detail);
        product.setProductSingleImages(pis_single);
        product.setProductDetailImages(pis_detail);

        List<PropertyValue> pvs = propertyValueService.queryByPid(pid);
        List<Review> reviews = reviewService.queryByPid(pid);

        productService.setSaleAndReviewNumber(product);
        productImageService.setFirstProductImage(product);

        Map<String,Object> map= new HashMap<>();
        map.put("product",product);
        map.put("pvs",pvs);
        map.put("reviews",reviews);

        return Result.success(map);
    }

    //模态登录--检查登录状态
    @GetMapping("forecheckLogin")
    public Object checkLogin(HttpSession session) throws Exception{
        User user =(User) session.getAttribute("u");
        if(null!=user)
            return Result.success();
        else
            return Result.fail("未登录");
    }

    //分类页
    @GetMapping("forecategory/{cid}")
    public Object category(@PathVariable int cid,String sort) throws Exception{
        Category c = categoryService.get(cid);
        productService.fill(c);
        productService.setSaleAndReviewNumber(c.getProducts());

        if(null!=sort){
            switch(sort){
                case "review":
                    Collections.sort(c.getProducts(),new ProductReviewComparator());
                    break;
                case "date" :
                    Collections.sort(c.getProducts(),new ProductDateComparator());
                    break;

                case "saleCount" :
                    Collections.sort(c.getProducts(),new ProductSaleCountComparator());
                    break;

                case "price":
                    Collections.sort(c.getProducts(),new ProductPriceComparator());
                    break;

                case "all":
                    Collections.sort(c.getProducts(),new ProductAllComparator());
                    break;
            }
        }

        return c;

    }

    //搜索
    @PostMapping("foresearch")
    public Object search(String keyword) throws Exception{
//        if(null==keyword)
//            keyword = "";
        List<Product> ps = productService.queryByKeyword(keyword);
        return ps;
    }

    //立即购买
    @GetMapping("forebuyone")
    public Object buyone(int pid, int num, HttpSession session){
        return buyoneAndAddCart(pid,num,session);
    }

    //立即购买和加入购物车都会用到的方法
    private int buyoneAndAddCart(int pid, int num, HttpSession session) {
        Product product = productService.get(pid);
        int oiid = 0;

        User user =(User)  session.getAttribute("u");
        boolean found = false;
        List<OrderItem> ois = orderItemService.queryByUidAndOrderIsNull(user.getId());
        for (OrderItem oi : ois) {
            if(oi.getProduct().getId()==product.getId()){
                oi.setNumber(oi.getNumber()+num);
                orderItemService.update(oi);
                found = true;
                oiid = oi.getId();
                break;
            }
        }

        if(!found){
            OrderItem oi = new OrderItem();
            oi.setUid(user.getId());
            oi.setPid(product.getId());
            oi.setNumber(num);
            orderItemService.add(oi);
            oiid = oi.getId();
        }
        return oiid;
    }

    //结算页面
    @GetMapping("forebuy")
    public Object buy(String[] oiid,HttpSession session) throws Exception{
        List<OrderItem> orderItems = new ArrayList<>();
        float total = 0;

        for (String strid : oiid) {
            int id = Integer.parseInt(strid);
            OrderItem oi= orderItemService.get(id);
            total +=oi.getProduct().getPromotePrice()*oi.getNumber();
            orderItems.add(oi);
        }
//        productImageService.setFirstProdutImagesOnOrderItems(orderItems);

        session.setAttribute("ois", orderItems);

        Map<String,Object> map = new HashMap<>();
        map.put("orderItems", orderItems);
        map.put("total", total);
        return Result.success(map);
    }

    //加入购物车
    @GetMapping("foreaddCart")
    public Object addCart(int pid, int num, HttpSession session) throws Exception{
        buyoneAndAddCart(pid,num,session);
        return Result.success();
    }

    //查看购物车
    @GetMapping("forecart")
    public Object cart(HttpSession session) {
        User user =(User)  session.getAttribute("u");
        List<OrderItem> ois = orderItemService.queryByUidAndOrderIsNull(user.getId());
        return ois;
    }

    //查看购物车，删除orderItem
    @GetMapping("foredeleteOrderItem")
    public Object deleteOrderItem(HttpSession session,int oiid){
        User user =(User)  session.getAttribute("u");
        if(null==user)
            return Result.fail("未登录");
        orderItemService.delete(oiid);
        return Result.success();
    }

    //查看购物车，更改订单商品的数量
    @GetMapping("forechangeOrderItem")
    public Object changeOrderItem( HttpSession session, int pid, int num) {
        User user =(User)  session.getAttribute("u");
        if(null==user)
            return Result.fail("未登录");

        List<OrderItem> ois = orderItemService.queryByUidAndOrderIsNull(user.getId());
        for (OrderItem oi : ois) {
            if(oi.getProduct().getId()==pid){
                oi.setNumber(num);
                orderItemService.update(oi);
                break;
            }
        }
        return Result.success();
    }

    //生成订单
    @PostMapping("forecreateOrder")
    public Object createOrder(@RequestBody Order order,HttpSession session){
        User user =(User)  session.getAttribute("u");
        if(null==user)
            return Result.fail("未登录");
        String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + RandomUtils.nextInt(10000);
        order.setOrderCode(orderCode);
        order.setCreateDate(new Date());
        order.setUid(user.getId());
        order.setStatus(OrderService.waitPay);
        List<OrderItem> ois= (List<OrderItem>)  session.getAttribute("ois");

        float total =orderService.add(order,ois);  //使用事务处理

        Map<String,Object> map = new HashMap<>();
        map.put("oid", order.getId());
        map.put("total", total);

        return Result.success(map);
    }

    //支付成功
    @GetMapping("forepayed")
    public Object payed(int oid) {
        Order order = orderService.get(oid);
        order.setStatus(OrderService.waitDelivery);
        order.setPayDate(new Date());
        orderService.update(order);
        return order;
    }

    //我的订单页
    @GetMapping("forebought")
    public Object bought(HttpSession session) {
        User user =(User)  session.getAttribute("u");
        if(null==user)
            return Result.fail("未登录");
        List<Order> os = orderService.queryOrderByUid(user.getId(), OrderService.delete);
        orderItemService.fill(os);
        return os;
    }

    //我的订单页操作--删除
    @PutMapping("foredeleteOrder")
    public Object deleteOrder(int oid){
        Order o = orderService.get(oid);
        o.setStatus(OrderService.delete);
        orderService.update(o);
        return Result.success();
    }

    //我的订单页操作--确认收货
    @GetMapping("foreconfirmPay")
    public Object confirmPay(int oid) {
        Order o = orderService.get(oid);
        orderItemService.fill(o);
        return o;
    }

    //我的订单页操作--确认收货后，点击了确认支付
    @GetMapping("foreorderConfirmed")
    public Object orderConfirmed( int oid) {
        Order o = orderService.get(oid);
        o.setStatus(OrderService.waitReview);
        o.setConfirmDate(new Date());
        orderService.update(o);
        return Result.success();
    }

    //评价产品
    @GetMapping("forereview")
    public Object review(int oid) {
        Order o = orderService.get(oid);
        orderItemService.fill(o);
        Product p = o.getOrderItems().get(0).getProduct();
        List<Review> reviews = reviewService.queryByPid(p.getId());
        productService.setSaleAndReviewNumber(p);
        Map<String,Object> map = new HashMap<>();
        map.put("p", p);
        map.put("o", o);
        map.put("reviews", reviews);

        return Result.success(map);
    }

    //提交评价
    @PostMapping("foredoreview")
    public Object doreview( HttpSession session,int oid,int pid,String content) {
        Order o = orderService.get(oid);
        o.setStatus(OrderService.finish);
        orderService.update(o);

        Product p = productService.get(pid);
        content = HtmlUtils.htmlEscape(content);

        User user =(User)  session.getAttribute("u");
        Review review = new Review();
        review.setContent(content);
        review.setPid(p.getId());
        review.setCreateDate(new Date());
        review.setUid(user.getId());
        reviewService.add(review);
        return Result.success();
    }

}
