package com.example.mymiaosha2.redis;

public interface KeyPrefix {

    public int expireSeconds();

    public String getPrefix();
}
