package com.example.mymiaosha4.service;

import com.example.mymiaosha4.dao.UserDao;
import com.example.mymiaosha4.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getById(Integer id){
        return userDao.getById(id);
    }

    @Transactional          //注释掉后，虽然会报错，但是可以把id为2的用户插入数据库
    public Boolean tx(){
        User user1 = new User();
        user1.setId(2);
        user1.setName("2222");
        userDao.insert(user1);

        User user2 = new User();
        user2.setId(1);
        user2.setName("1111");
        userDao.insert(user2);

        return true;
    }
}
