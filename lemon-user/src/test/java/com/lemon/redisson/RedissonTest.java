package com.lemon.redisson;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBucket;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@Component
public class RedissonTest {

    @Autowired
    private RedissonClient redisson;

    @Autowired
    private RedisTemplate<String, String> template;

    @Test
    public void set() {
        redisson.getKeys().flushall();
        RBucket<String> rBucket =  redisson.getBucket("key");
        rBucket.set("1231");
        RMap<String, String> m = redisson.getMap("test", StringCodec.INSTANCE);
        m.put("1", "2");
        BoundHashOperations<String, String, String> hash = template.boundHashOps("test");
        String t = hash.get("1");
        assertThat(t).isEqualTo("2");
    }

}