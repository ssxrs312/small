package study.small.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import study.small.entity.Category;
import study.small.entity.OrderItem;
import study.small.entity.User;
import study.small.service.CategoryService;
import study.small.service.OrderItemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class OtherInterceptor implements HandlerInterceptor {
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    CategoryService categoryService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        //这里是获取购物车中一共有多少数量
        HttpSession session = request.getSession();
        User user =(User) session.getAttribute("u");
        int  cartTotalItemNumber = 0;  //购物车的总数量
        if(null!=user) {
            List<OrderItem> ois = orderItemService.queryByUidAndOrderIsNull(user.getId());
            for (OrderItem oi : ois) {
                cartTotalItemNumber+=oi.getNumber();
            }
        }
        session.setAttribute("cartTotalItemNumber", cartTotalItemNumber);

        //这里是获取分类集合信息，用于放在搜索栏下面
        List<Category> cs = categoryService.queryAll();
        request.getServletContext().setAttribute("categories_below_search", cs);

        //这里是获取当前的contextPath:small,用与放在左上角那个变形金刚，点击之后才能够跳转到首页，否则点击之后也仅仅停留在当前页面
        String contextPath=request.getServletContext().getContextPath();
        request.getServletContext().setAttribute("contextPath", contextPath);

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
    }
}
