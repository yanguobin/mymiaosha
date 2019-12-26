package com.example.mymiaosha7.service;

import com.example.mymiaosha7.domain.MiaoshaOrder;
import com.example.mymiaosha7.domain.MiaoshaUser;
import com.example.mymiaosha7.domain.OrderInfo;
import com.example.mymiaosha7.redis.MiaoshaKey;
import com.example.mymiaosha7.redis.MyRedisUtil;
import com.example.mymiaosha7.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MiaoshaService {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    MyRedisUtil myRedisUtil;

    @Transactional      //进行秒杀步骤：减库存 创建普通订单 创建秒杀订单    注意这是个事务操作
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goodsVo) {
        //减库存
        boolean success = goodsService.reduceStock(goodsVo);
        if (success){
            //创建普通订单、创建秒杀订单  注意这是个事务操作
            return orderService.createOrder(user, goodsVo);
        }else {
            setGoodsOver(goodsVo.getId());
            return null;
        }
    }

    public long getMiaoshaResult(Long userId, long goodsId) {
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
        if (order != null){
            return order.getOrderId();
        }else {
            boolean isOver = getGoodsOver(goodsId);
            if (isOver){
                return -1;
            }else {
                return 0;
            }
        }
    }

    private void setGoodsOver(Long goodsId) {
        myRedisUtil.set(MiaoshaKey.isGoodsOver, ""+goodsId, true);
    }

    private boolean getGoodsOver(long goodsId){
        return myRedisUtil.exists(MiaoshaKey.isGoodsOver, ""+goodsId);
    }
}
