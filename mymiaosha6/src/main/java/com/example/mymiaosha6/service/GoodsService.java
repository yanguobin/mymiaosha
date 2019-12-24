package com.example.mymiaosha6.service;

import com.example.mymiaosha6.dao.GoodsDao;
import com.example.mymiaosha6.domain.MiaoshaGoods;
import com.example.mymiaosha6.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    GoodsDao goodsDao;

    public List<GoodsVo> listGoodsVo(){
        return goodsDao.listGoodsVo();
    }

    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }

    public boolean reduceStock(GoodsVo goodsVo) {
        MiaoshaGoods miaoshaGoods = new MiaoshaGoods();
        miaoshaGoods.setGoodsId(goodsVo.getId());
        int ret = goodsDao.reduceStock(miaoshaGoods);     //减库存其实就是根据商品id更新商品库存为原来库存减1，库存减1可以直接放到sql语句中实现
        return ret > 0;
    }
}
