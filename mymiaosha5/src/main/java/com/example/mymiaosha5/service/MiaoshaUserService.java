package com.example.mymiaosha5.service;

import com.example.mymiaosha5.dao.MiaoshaUserDao;
import com.example.mymiaosha5.domain.MiaoshaUser;
import com.example.mymiaosha5.exception.GlobalException;
import com.example.mymiaosha5.redis.MiaoshaUserKey;
import com.example.mymiaosha5.redis.MyRedisUtil;
import com.example.mymiaosha5.result.CodeMsg;
import com.example.mymiaosha5.util.MD5Util;
import com.example.mymiaosha5.util.UUIDUtil;
import com.example.mymiaosha5.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class MiaoshaUserService {

    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    @Autowired
    MyRedisUtil myRedisUtil;

    public MiaoshaUser getById(long id){
        //取缓存
        MiaoshaUser miaoshaUser = myRedisUtil.get(MiaoshaUserKey.getById, ""+id, MiaoshaUser.class);
        if (miaoshaUser != null){
            return miaoshaUser;
        }
        //取数据库
        miaoshaUser = miaoshaUserDao.getById(id);
        if (miaoshaUser != null){
            myRedisUtil.set(MiaoshaUserKey.getById, ""+id, miaoshaUser);
        }
        return miaoshaUser;
    }

    public boolean updatePassword(String token, long id, String formPass){
        //取user
        MiaoshaUser miaoshaUser = getById(id);
        if (miaoshaUser == null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //更新数据库
        MiaoshaUser toBeUpdate = new MiaoshaUser();
        toBeUpdate.setId(id);
        toBeUpdate.setPassword(MD5Util.formPassToDBPass(formPass, miaoshaUser.getSalt()));
        miaoshaUserDao.update(toBeUpdate);
        //处理缓存
        myRedisUtil.delete(MiaoshaUserKey.getById, ""+id);
        miaoshaUser.setPassword(toBeUpdate.getPassword());
        myRedisUtil.set(MiaoshaUserKey.token, token, miaoshaUser);
        return true;
    }

    public boolean login(HttpServletResponse response, LoginVo loginVo){      //应该返回表达业务方法含义的类型，而不应该是CodeMsg
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
        //生成cookie
        String token = UUIDUtil.uuid();     //没必要每次都生成一个新的token，所以这里拿了出来
        addCookie(response, token, user);
        return true;
    }

    public MiaoshaUser getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)){
            return null;
        }
        MiaoshaUser user = myRedisUtil.get(MiaoshaUserKey.token, token, MiaoshaUser.class);
        //延长有效期，保证有效期总是最后一次访问时间再加上session过期时间
        if (user != null){
            addCookie(response, token, user);      //重新往缓存中设置token，并生成新的cookie，这样就达到了延长有效期的目的
        }
        return user;
    }

    public void addCookie(HttpServletResponse response, String token, MiaoshaUser user){
        myRedisUtil.set(MiaoshaUserKey.token, token, user); //将user和token绑定并存入Redis中
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);   //根据token生成cookie
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie); //将cookie放入response客户端中
    }
}
