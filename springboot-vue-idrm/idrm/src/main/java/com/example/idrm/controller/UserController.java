package com.example.idrm.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.idrm.common.QueryPageParam;
import com.example.idrm.entity.User;
import com.example.idrm.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cxd
 * @since 2024-03-25
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @GetMapping("/list")
    public List<User> list(){
        return userService.list();
    }

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable Integer id){
        return userService.getById(id);
    }

    @PostMapping("/listCondition")
    public List<User> listCondition(@RequestBody User user){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(User::getName,user.getName());
        return userService.list(queryWrapper);
    }

    @PostMapping("/pageCondition")
    public Page<User> pageCondition(@RequestBody QueryPageParam<User> queryParam){
        Page<User> page = new Page<>(queryParam.getPageNumber(), queryParam.getPageSize());
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        User queryParams = queryParam.getParams();
        queryWrapper.like(User::getName,queryParams.getName());
        return userService.page(page, queryWrapper);
    }

    /**
     * 自定义方法，使用Wrapper
     * @param queryParam 请求参数
     * @return
     */
    @PostMapping("/pageConditionC")
    public Page<User> pageConditionC(@RequestBody QueryPageParam<User> queryParam){
        Page<User> page = new Page<>(queryParam.getPageNumber(), queryParam.getPageSize());
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        User queryParams = queryParam.getParams();
        queryWrapper.like(User::getName,queryParams.getName());
        return userService.pageC(page, queryWrapper);
    }

    @PostMapping("/save")
    public boolean save(@RequestBody User user){
        return  userService.save(user);
    }

    @PostMapping("/update")
    public boolean update(@RequestBody User user){
        return userService.updateById(user);
    }

    @PostMapping("/saveOrUpdate")
    public boolean saveOrUpdate(@RequestBody User user){
        return userService.saveOrUpdate(user);
    }

    @DeleteMapping("delete/{id}")
    public boolean delete(@PathVariable Integer id){
        return userService.removeById(id);
    }

}
