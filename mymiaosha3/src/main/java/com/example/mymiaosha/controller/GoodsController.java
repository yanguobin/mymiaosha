package com.example.mymiaosha.controller;

import com.example.mymiaosha.domain.Goods;
import com.example.mymiaosha.domain.MiaoshaUser;
import com.example.mymiaosha.service.GoodsService;
import com.example.mymiaosha.service.MiaoshaUserService;
import com.example.mymiaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/to_list")
    public String list(Model model, MiaoshaUser user) {
        model.addAttribute("user", user);
        //查询商品列表，包括商品和秒杀商品
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsList);     //放到Model中，供前端展示使用。
        return "goods_list";
    }

}
