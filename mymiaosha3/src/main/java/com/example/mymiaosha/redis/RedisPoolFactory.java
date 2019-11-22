package com.example.mymiaosha.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class RedisPoolFactory {

    @Autowired
    MyRedisConfig myRedisConfig;

    @Bean
    public JedisPool jedisPoolFactory(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(myRedisConfig.getPoolMaxTotal());
        jedisPoolConfig.setMaxIdle(myRedisConfig.getPoolMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(myRedisConfig.getPoolMaxWait() * 1000);    //自定义的单位为秒，而源码需要的是毫秒
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, myRedisConfig.getHost(), myRedisConfig.getPort(), myRedisConfig.getTimeout() * 1000);  //自定义的单位为秒，而源码需要的是毫秒
        return jedisPool;
    }
}
