package com.example.mymiaosha5.redis;

public interface KeyPrefix {

    public int expireSeconds();

    public String getPrefix();
}
