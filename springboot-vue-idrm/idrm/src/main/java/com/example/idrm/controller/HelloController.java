package com.example.idrm.controller;

import com.example.idrm.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.idrm.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author cxdpc
 * @date 2024/3/21 16:36
 */
@RestController
public class HelloController {

    @Resource
    private UserService userService;

    @GetMapping
    public String hello(){
        return "hello";
    }

    @GetMapping("/list")
    public List<User> list(){
        return userService.list();
    }

    @GetMapping("/findByNo/{no}")
    public User findByNo(@PathVariable("no") String no){
        return userService.findUserByNo(no);
    }
}
