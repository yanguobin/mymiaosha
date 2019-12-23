package com.example.mymiaosha6.util;

import java.util.UUID;

public class UUIDUtil {
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-", "");   //去掉原生自带的"-"
    }
}
