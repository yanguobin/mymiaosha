package com.example.mymiaosha.redis;

public interface KeyPrefix {

    public int expireSeconds();

    public String getPrefix();
}
