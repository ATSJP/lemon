package com.lemon.tools;

import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redisson工具
 * 
 * @author sjp
 * @date 2019/1/16
 **/
@Service
public class RedissonTools {

	private Logger			logger	= LoggerFactory.getLogger(getClass());

	@Resource
	private RedissonClient	redissonClient;

	public <T> T get(String name) {
		RBucket<T> r = redissonClient.getBucket(name);
		return r.get();
	}

	public <T> void set(String name, T value) {
		RBucket<T> r = redissonClient.getBucket(name);
		r.setAsync(value);
	}

	public <T> T set(String name, T value, int expiredSeconds) {
		RBucket<T> r = redissonClient.getBucket(name);
		r.setAsync(value, expiredSeconds, TimeUnit.SECONDS);
		return r.get();
	}

	public <K, V> Map<K, V> getMap(String name) {
		RMap<K, V> map = redissonClient.getMap(name);
		return map.readAllMap();
	}

	public <K, V> void setMap(String name, Map map) {
		RMap<K, V> r = redissonClient.getMap(name);
		r.putAllAsync(map);
	}

	public void delete(String name) {
		redissonClient.getBucket(name).deleteAsync();
	}

	public boolean tryLock(String key, int timeout, int expires) {
		RLock rLock = redissonClient.getLock(key);
		try {
			// 获取锁有timeout等待时间 expires后释放锁
			return rLock.tryLock(timeout, expires, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			logger.error("try lock fail，key:{} exception:{}", key, e.getMessage());
			throw new RuntimeException("try lock fail，key:" + key, e);
		}
	}

	public void unlockNoWait(String key) {
		RLock rLock = redissonClient.getLock(key);
		rLock.unlock();
	}

}
