package com.lemon.tools;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sjp
 * @date 2019/1/16
 **/
@Service
public class RedissonTools {

    @Resource
    private RedissonClient redissonClient;

    @SuppressWarnings("unchecked")
    public void set(String key, String value) {
        RBucket rBucket = redissonClient.getBucket(key);
        rBucket.setAsync(value);
    }

    public String get(String key) {
        RBucket<String> rBucket = redissonClient.getBucket(key);
        return rBucket.get();
    }

}
