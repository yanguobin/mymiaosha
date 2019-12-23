package com.example.mymiaosha6.redis;

public interface KeyPrefix {

    public int expireSeconds();

    public String getPrefix();
}
