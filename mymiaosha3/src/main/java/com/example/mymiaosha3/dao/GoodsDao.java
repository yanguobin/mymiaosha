package com.example.mymiaosha3.dao;

import com.example.mymiaosha3.domain.MiaoshaGoods;
import com.example.mymiaosha3.vo.GoodsVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GoodsDao {

    @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "goodsName", column = "goods_name"),
            @Result(property = "goodsTitle", column = "goods_title"),
            @Result(property = "goodsImg", column = "goods_img"),
            @Result(property = "goodsDetail", column = "goods_detail"),
            @Result(property = "goodsPrice", column = "goods_price"),
            @Result(property = "goodsStock", column = "goods_stock"),
            @Result(property = "miaoshaPrice", column = "miaosha_price"),
            @Result(property = "stockCount", column = "stock_count"),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "endDate", column = "end_date")
    })
    public List<GoodsVo> listGoodsVo();   //goods表和miaosha_goods表之间做联合查询，接收的实体对象为自己封装的GoodsVo类。

    @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.miaosha_price from miaosha_goods mg left join goods g on mg.goods_id = g.id where g.id = #{goodsId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "goodsName", column = "goods_name"),
            @Result(property = "goodsTitle", column = "goods_title"),
            @Result(property = "goodsImg", column = "goods_img"),
            @Result(property = "goodsDetail", column = "goods_detail"),
            @Result(property = "goodsPrice", column = "goods_price"),
            @Result(property = "goodsStock", column = "goods_stock"),
            @Result(property = "miaoshaPrice", column = "miaosha_price"),
            @Result(property = "stockCount", column = "stock_count"),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "endDate", column = "end_date")
    })
    public GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);

    @Update("update miaosha_goods set stock_count = stock_count - 1 where goods_id = #{goodsId}")
    public int reduceStock(MiaoshaGoods miaoshaGoods);
}
