package com.example.mymiaosha5.redis;

public class OrderKey extends BasePrefix {
    public OrderKey(String prefix) {
        super(prefix);
    }
    public static OrderKey getMiaoshaOrderByUidGid = new OrderKey("moug");
}
