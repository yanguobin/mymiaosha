package com.example.mymiaosha7.redis;

public interface KeyPrefix {

    public int expireSeconds();

    public String getPrefix();
}
