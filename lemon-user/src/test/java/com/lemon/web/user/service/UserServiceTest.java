package com.lemon.web.user.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * UserServiceTest
 *
 * @author sjp
 * @date 2019/5/4
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Resource
	private UserService userService;

	@Test
	public void register() {
		userService.register("test9", "test9", "test9");
	}
}