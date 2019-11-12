package com.example.mymiaosha2.service;

import com.example.mymiaosha2.dao.UserDao;
import com.example.mymiaosha2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getById(Integer id){
        return userDao.getById(id);
    }
}
