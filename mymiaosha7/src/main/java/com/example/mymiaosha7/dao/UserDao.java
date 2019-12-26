package com.example.mymiaosha7.dao;

import com.example.mymiaosha7.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    @Select("select * from user where id = #{id}")
    public User getById(@Param("id") Integer id);

    @Insert("insert into user values(#{id}, #{name})")
    public Integer insert(User user);
}
