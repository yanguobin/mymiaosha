package com.example.mymiaosha4.redis;

public interface KeyPrefix {

    public int expireSeconds();

    public String getPrefix();
}
