package com.example.mymiaosha4.redis;

public class UserKey extends BasePrefix {

    public UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");  //传进去的是id，经过父类加工后，返回来的是UserKey:id
    public static UserKey getByName = new UserKey("name");
}
