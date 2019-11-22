package com.example.mymiaosha2.controller;

import com.example.mymiaosha2.domain.MiaoshaUser;
import com.example.mymiaosha2.redis.MyRedisUtil;
import com.example.mymiaosha2.result.Result;
import com.example.mymiaosha2.service.MiaoshaUserService;
import com.example.mymiaosha2.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @RequestMapping("/to_list")
    public String list(Model model) {
        model.addAttribute("user", new MiaoshaUser());
        return "goods_list";
    }

}
