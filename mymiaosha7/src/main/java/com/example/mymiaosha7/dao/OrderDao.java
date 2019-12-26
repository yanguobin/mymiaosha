package com.example.mymiaosha7.dao;

import com.example.mymiaosha7.domain.MiaoshaOrder;
import com.example.mymiaosha7.domain.OrderInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrderDao {

    @Select("select * from miaosha_order where user_id=#{userId} and goods_id=#{goodsId}")
    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(@Param("userId")long userId, @Param("goodsId")long goodsId);

    @Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date) values" +
            "(#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel}, #{status}, #{createDate})")
    @SelectKey(keyColumn = "id", keyProperty = "id", resultType = long.class, before = false, statement = "select last_insert_id()")    //使得该方法返回id
    public long insert(OrderInfo orderInfo);

    @Insert("insert into miaosha_order(user_id, goods_id, order_id) values(#{userId}, #{goodsId}, #{orderId})")
    public int insertMiaoshaOrder(MiaoshaOrder miaoshaOrder);

    @Select("select * from order_info where id = #{orderId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "goodsId", column = "goods_id"),
            @Result(property = "deliveryAddrId", column = "delivery_addr_id"),
            @Result(property = "goodsName", column = "goods_name"),
            @Result(property = "goodsCount", column = "goods_count"),
            @Result(property = "goodsPrice", column = "goods_price"),
            @Result(property = "orderChannel", column = "order_channel"),
            @Result(property = "status", column = "status"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "payDate", column = "pay_date")
    })
    public OrderInfo getOrderById(@Param("orderId") long orderId);
}
