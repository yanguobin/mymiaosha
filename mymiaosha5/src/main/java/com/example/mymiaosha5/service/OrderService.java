package com.example.mymiaosha5.service;

import com.example.mymiaosha5.dao.OrderDao;
import com.example.mymiaosha5.domain.MiaoshaOrder;
import com.example.mymiaosha5.domain.MiaoshaUser;
import com.example.mymiaosha5.domain.OrderInfo;
import com.example.mymiaosha5.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class OrderService {

    @Autowired
    OrderDao orderDao;

    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(long userId, long goodsId) {
        return orderDao.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
    }

    @Transactional
    public OrderInfo createOrder(MiaoshaUser user, GoodsVo goodsVo) {
        //创建普通订单
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(user.getId());
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goodsVo.getId());
        orderInfo.setGoodsName(goodsVo.getGoodsName());
        orderInfo.setGoodsPrice(goodsVo.getMiaoshaPrice());     //这里是秒杀价格，而不是原价
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);     //新建订单，未支付
        long orderId = orderDao.insert(orderInfo);  //返回值是通过@SelectKey获取的
        //创建秒杀订单
        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setUserId(user.getId());
        miaoshaOrder.setGoodsId(goodsVo.getId());
        miaoshaOrder.setOrderId(orderId);
        orderDao.insertMiaoshaOrder(miaoshaOrder);
        return orderInfo;
    }
}
