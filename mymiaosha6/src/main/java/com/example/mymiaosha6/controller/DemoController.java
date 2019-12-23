package com.example.mymiaosha6.controller;

import com.example.mymiaosha6.domain.User;
import com.example.mymiaosha6.rabbitmq.MQSender;
import com.example.mymiaosha6.redis.MyRedisUtil;
import com.example.mymiaosha6.redis.UserKey;
import com.example.mymiaosha6.result.CodeMsg;
import com.example.mymiaosha6.result.Result;
import com.example.mymiaosha6.service.UserService;
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

    @Autowired
    MQSender mqSender;

    @RequestMapping("/mq/fanout")
    @ResponseBody
    public Result<String> fanout(){
        mqSender.sendFanout("hello,yanguobin");
        return Result.success("Hello,World");
    }

    @RequestMapping("/mq/topic")
    @ResponseBody
    public Result<String> topic(){
        mqSender.sendTopic("hello,yanguobin");
        return Result.success("Hello,World");
    }

    @RequestMapping("/mq")
    @ResponseBody
    public Result<String> mq(){
        mqSender.send("hello,yanguobin");
        return Result.success("Hello,World");
    }

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
    public Result<User> redisGet(){
        User user = myRedisUtil.get(UserKey.getById, ""+1, User.class);  //单一的键名容易重复被覆盖，应该拼装为更复杂的键，比如加前缀，键id变为UserKey:id1
        return Result.success(user);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet(){
        User user = new User();
        user.setId(1);
        user.setName("1111");
        myRedisUtil.set(UserKey.getById, ""+1, user);   //键id变为UserKey:id1
        return Result.success(true);
    }

}
