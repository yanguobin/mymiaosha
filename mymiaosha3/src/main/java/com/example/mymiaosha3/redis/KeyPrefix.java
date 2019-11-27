package com.example.mymiaosha3.redis;

public interface KeyPrefix {

    public int expireSeconds();

    public String getPrefix();
}
