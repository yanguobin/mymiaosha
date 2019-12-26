package com.example.mymiaosha7.dao;

import com.example.mymiaosha7.domain.MiaoshaUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MiaoshaUserDao {

    @Select("select * from miaosha_user where id = #{id}")
    public MiaoshaUser getById(@Param("id") long id);    //入参为long类型

    @Update("update miaosha_user set password = #{password} where id = #{id}")
    public void update(MiaoshaUser toBeUpdate);
}
