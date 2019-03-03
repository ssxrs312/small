package study.small.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.small.entity.Category;
import study.small.entity.User;
import study.small.service.UserService;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("users")
    public PageInfo<User> list(@RequestParam(value = "start",defaultValue = "1")int start,
                     @RequestParam(value = "size",defaultValue = "2")int size) throws Exception{

        PageHelper.startPage(start,size,"id desc");
        List<User> us = userService.queryAll();
        PageInfo<User> page = new PageInfo<>(us,5); //5表示导航分页最多有5个，像 [1,2,3,4,5] 这样
        return page;
    }
}
