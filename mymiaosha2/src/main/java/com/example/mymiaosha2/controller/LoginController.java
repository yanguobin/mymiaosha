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
    public Result<Boolean> doLogin(LoginVo loginVo){
        logger.info(loginVo.toString());
        //参数校验
        String passInput = loginVo.getPassword();
        String mobile = loginVo.getMobile();    //这是通过ajax做第一次md5加密后的密码
        if (StringUtils.isEmpty(passInput)){    //StringUtils类在commons-lang3依赖下
            return Result.error(CodeMsg.PASSWORD_EMPTY);
        }
        if (StringUtils.isEmpty(mobile)){
            return Result.error(CodeMsg.MOBILE_EMPTY);
        }
        if (!ValidatorUtil.isMobile(mobile)){
            return Result.error(CodeMsg.MOBILE_ERROR);
        }
        //登录
        CodeMsg codeMsg = miaoshaUserService.login(loginVo);
        if (codeMsg.getCode() == 0){
            return Result.success(true);
        }else {
            return Result.error(codeMsg);
        }
    }
}
