package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.QueryPageParam;
import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class HelloController {

    @GetMapping
    public String hello2(){
        return "hello wms";
    }

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> list(){
//        return userService.list();
        return userService.listAll();
    }

    //新增
    @PostMapping("/save")
    public boolean save(@RequestBody User user){
        return userService.save(user);
    }

    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody User user){
        return userService.updateById(user);
    }

    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody User user){
        return userService.saveOrUpdate(user);
    }
    //删除
    @GetMapping("/delete")
    public boolean delete(Integer id){
        return userService.removeById(id);
    }
    //查询（模糊，匹配）
    @PostMapping("/listP")
    public List<User> listP(@RequestBody User user){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getName,user.getName());
        return userService.list(lambdaQueryWrapper);
    }

    @PostMapping("/listPage")
//    public List<User> listPage(@RequestBody HashMap map){
    public Result listPage(@RequestBody QueryPageParam param){
        System.out.println(param);

        Page<User> page = new Page<>();
        page.setCurrent(param.getPageNum());
        page.setSize(param.getPageSize());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(User::getName,param.getParam().get("name"));
        lambdaQueryWrapper.like(User::getPassword,param.getParam().get("password"));
        IPage result = userService.page(page,lambdaQueryWrapper);
        System.out.println("total==="+result.getTotal());
        return Result.success(result.getRecords(),result.getTotal());
    }








}
