package com.lemon.tools;

import org.redisson.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
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

	/**
	 * 获取ScoredSorted sets的Size
	 *
	 * @param key key
	 */
	public int rScoredSortedSetSize(String key) {
		RScoredSortedSet<Object> rScoredSortedSet = redissonClient.getScoredSortedSet(key);
		return rScoredSortedSet.size();
	}

	/**
	 * 同步往ScoredSorted sets放入一个值
	 * 
	 * @param key key
	 * @param score score
	 * @param object object
	 */
	public void rScoredSortedSetAdd(String key, Double score, Object object) {
		RScoredSortedSet<Object> rScoredSortedSet = redissonClient.getScoredSortedSet(key);
		rScoredSortedSet.add(score, object);
	}

	/**
	 * 异步往ScoredSorted sets放入一个值
	 *
	 * @param key key
	 * @param score score
	 * @param object object
	 */
	public void rScoredSortedSetAddAsync(String key, Double score, Object object) {
		RScoredSortedSet<Object> rScoredSortedSet = redissonClient.getScoredSortedSet(key);
		rScoredSortedSet.addAsync(score, object);
	}

	/**
	 * 把Score加到指定Key下面的Object中，如果不存在则创建
	 * 
	 * @param key key
	 * @param score score
	 * @param object object
	 */
	public void rScoredSortedSetAddScore(String key, Double score, Object object) {
		RScoredSortedSet<Object> rScoredSortedSet = redissonClient.getScoredSortedSet(key);
		rScoredSortedSet.addScore(object, score);
	}

	/**
	 * 获取当前对象对指定Key下的Object排行
	 *
	 * @param key key
	 * @param object object
	 */
	public int rScoredSortedSetRankIndex(String key, Object object) {
		RScoredSortedSet<Object> rScoredSortedSet = redissonClient.getScoredSortedSet(key);
		return rScoredSortedSet.rank(object);
	}

	/**
	 * 获取指定Key下正序排行榜 (-1 代表最后一个) -2 （代表倒数第二个）
	 * 
	 * @param key key
	 * @param startIndex startIndex
	 * @param endIndex endIndex
	 * @return Collection<Object>
	 */
	public Collection<Object> rScoredSortedSetRank(String key, int startIndex, int endIndex) {
		RScoredSortedSet<Object> rScoredSortedSet = redissonClient.getScoredSortedSet(key);
		return rScoredSortedSet.valueRange(startIndex, endIndex);
	}

	/**
	 * 获取指定Key下倒序排行榜 (-1 代表最后一个) -2 （代表倒数第二个）
	 *
	 * @param key key
	 * @param startIndex startIndex
	 * @param endIndex endIndex
	 * @return Collection<Object>
	 */
	public Collection<Object> rScoredSortedSetRevRank(String key, int startIndex, int endIndex) {
		RScoredSortedSet<Object> rScoredSortedSet = redissonClient.getScoredSortedSet(key);
		return rScoredSortedSet.valueRangeReversed(startIndex, endIndex);
	}

	/**
	 * 删除ScoredSorted sets
	 * 
	 * @param key key
	 */
	public void rScoredSortedSetDeleteAll(String key) {
		RScoredSortedSet<Object> rScoredSortedSet = redissonClient.getScoredSortedSet(key);
		rScoredSortedSet.clear();
	}

}
