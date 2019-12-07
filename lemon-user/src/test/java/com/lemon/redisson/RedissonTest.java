package com.lemon.redisson;

import com.lemon.dto.TestDto;
import com.lemon.tools.RedissonTools;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBucket;
import org.redisson.api.RMap;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.client.protocol.ScoredEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@Component
public class RedissonTest {

	@Autowired
	private RedissonClient					redisson;
	@Autowired
	private RedissonTools					redissonTools;

	@Autowired
	private RedisTemplate<String, String>	template;

	@Test
	public void set() {
		redisson.getKeys().flushall();
		RBucket<String> rBucket = redisson.getBucket("key");
		rBucket.set("1231");
		RMap<String, String> m = redisson.getMap("test", StringCodec.INSTANCE);
		m.put("1", "2");
		BoundHashOperations<String, String, String> hash = template.boundHashOps("test");
		String t = hash.get("1");
		assertThat(t).isEqualTo("2");
	}

	/**
	 * 测试ScoredSortedSet
	 */
	@Test
	public void toolsTest() {
		String key = "test_zset";
		redissonTools.rScoredSortedSetDeleteAll(key);

		redissonTools.rScoredSortedSetAddAsync(key, 0.8, new TestDto(1L, "abc"));
		redissonTools.rScoredSortedSetAdd(key, 0.6, new TestDto(2L, "abc"));
		redissonTools.rScoredSortedSetAdd(key, 1.2, new TestDto(3L, "abc"));
		redissonTools.rScoredSortedSetAdd(key, 0.2, new TestDto(4L, "abc"));

		int index = redissonTools.rScoredSortedSetRankIndex(key, new TestDto(2L, "abc"));
		assert index == 1;

		Collection<Object> testDtoCollection = redissonTools.rScoredSortedSetRank(key, 0, -1);
		assert testDtoCollection != null && !testDtoCollection.isEmpty();
		testDtoCollection.forEach(System.out::println);

		RScoredSortedSet<Object> rScoredSortedSet = redisson.getScoredSortedSet(key);
		Collection<ScoredEntry<Object>> scoredEntries = rScoredSortedSet.entryRange(0, -1);
		scoredEntries.forEach(System.out::println);

		testDtoCollection = redissonTools.rScoredSortedSetRevRank(key, 0, -2);
		assert testDtoCollection != null && !testDtoCollection.isEmpty();
		testDtoCollection.forEach(System.out::println);

		redissonTools.rScoredSortedSetDeleteAll(key);

		index = redissonTools.rScoredSortedSetSize(key);
		assert index == 0;
	}

}
