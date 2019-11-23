package com.example.mymiaosha.service;

import com.example.mymiaosha.dao.GoodsDao;
import com.example.mymiaosha.domain.Goods;
import com.example.mymiaosha.vo.GoodsVo;
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
}
