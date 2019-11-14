package com.example.mymiaosha2.controller;

import com.example.mymiaosha2.domain.User;
import com.example.mymiaosha2.redis.MyRedisUtil;
import com.example.mymiaosha2.result.CodeMsg;
import com.example.mymiaosha2.result.Result;
import com.example.mymiaosha2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    UserService userService;

    @Autowired
    MyRedisUtil myRedisUtil;

    @RequestMapping("/")
    @ResponseBody
    public String home(){   //注意访问该方法的地址为http://localhost:8080/demo/  最后的"/"不能省略，对应@RequestMapping("/")
        return "Hello World!";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> hello(){
//        return new Result(0, "success", "hello,小老弟");
        return Result.success("hello,小老弟");
    }

    @RequestMapping("/helloError")
    @ResponseBody
    public Result helloError(){
//        return new Result(500100, "服务器异常");
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name", "小老弟");
        return "hello";
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet(){
        return Result.success(userService.getById(1));
    }

    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> dbTx(){
        userService.tx();
        return Result.success(true);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<Long> redisGet(){
        Long v1 = myRedisUtil.get("key1", Long.class);
        return Result.success(v1);
    }

    @RequestMapping("/redis/set") 
    @ResponseBody
    public Result<String> redisSet(){
        Boolean ret = myRedisUtil.set("key2", "hello,小老弟");
        String str = myRedisUtil.get("key2", String.class);
        return Result.success(str);
    }
}
