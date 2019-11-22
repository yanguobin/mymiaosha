package com.example.mymiaosha2.service;

import com.example.mymiaosha2.dao.MiaoshaUserDao;
import com.example.mymiaosha2.domain.MiaoshaUser;
import com.example.mymiaosha2.exception.GlobalException;
import com.example.mymiaosha2.result.CodeMsg;
import com.example.mymiaosha2.util.MD5Util;
import com.example.mymiaosha2.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.mymiaosha2.util.MD5Util.formPassToDBPass;

@Service
public class MiaoshaUserService {

    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    public MiaoshaUser getById(long id){
        return miaoshaUserDao.getById(id);
    }

    public boolean login(LoginVo loginVo){      //应该返回表达业务方法含义的类型，而不应该是CodeMsg
        if (loginVo == null){
            throw new GlobalException(CodeMsg.SERVER_ERROR);        //出现异常直接往外抛
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();       //通过ajax做第一次md5加密后的密码
        //判断数据库中是否存在该手机号
        MiaoshaUser user = getById(Long.parseLong(mobile));    //由于MiaoshaUserDao中的getById方法用到的入参为long类型，所以此处需要先转为long类型
        if (user == null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);
        if (!calcPass.equals(dbPass)){
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        return true;
    }
}
