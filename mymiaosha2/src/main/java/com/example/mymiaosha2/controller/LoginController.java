package com.example.mymiaosha2.controller;

import com.example.mymiaosha2.redis.MyRedisUtil;
import com.example.mymiaosha2.result.CodeMsg;
import com.example.mymiaosha2.result.Result;
import com.example.mymiaosha2.service.MiaoshaUserService;
import com.example.mymiaosha2.util.ValidatorUtil;
import com.example.mymiaosha2.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @Autowired
    MyRedisUtil myRedisUtil;

    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(@Valid LoginVo loginVo){
        logger.info(loginVo.toString());
        //登录
        miaoshaUserService.login(loginVo);
        return Result.success(true);
    }
}
