package com.example.mymiaosha1.controller;

import com.example.mymiaosha1.result.CodeMsg;
import com.example.mymiaosha1.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class DemoController {

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
}
