package study.small.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ForePageController {

    @GetMapping("/")
    public String index(){
        return "redirect:home";
    }

    @GetMapping("home")
    public String home(){
        return "fore/home";
    }

    @GetMapping("register")
    public String register(){
        return "fore/register";
    }

    @GetMapping("registerSuccess")
    public String registerSuccess(){
        return "fore/registerSuccess";
    }

    @GetMapping("login")
    public String login(){
        return "fore/login";
    }

    //退出
    @GetMapping("forelogout")
    public String logout(HttpSession session) {
        session.removeAttribute("u");
        return "redirect:home";
    }

    @GetMapping("product")
    public String product(){
        return "fore/product";
    }

    @GetMapping("category")
    public String category(){
        return "fore/category";
    }

    @GetMapping("search")
    public String search(){
        return "fore/searchResult";
    }

    @GetMapping("buy")
    public String buy(){
        return "fore/buy";
    }

    @GetMapping(value="/cart")
    public String cart(){
        return "fore/cart";
    }

    @GetMapping(value="/alipay")
    public String alipay(){
        return "fore/alipay";
    }

    @GetMapping(value="/payed")
    public String payed(){
        return "fore/payed";
    }

    @GetMapping(value="/bought")
    public String bought(){
        return "fore/bought";
    }

    @GetMapping(value="/confirmPay")
    public String confirmPay(){
        return "fore/confirmPay";
    }

    @GetMapping(value="/orderConfirmed")
    public String orderConfirmed(){
        return "fore/orderConfirmed";
    }

    @GetMapping(value="/review")
    public String review(){
        return "fore/review";
    }
}
