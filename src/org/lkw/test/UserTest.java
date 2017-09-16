package org.lkw.test;

import org.junit.Test;
import org.lkw.user.Service.UserService;
import org.lkw.user.entity.UserPO;

public class UserTest {

	@Test
	public void test() {
		UserService userService = new UserService();
		UserPO userPO = new UserPO();
		userPO.setAccount("lkw");
		userPO.setPassword("123");
		boolean isSuccess = userService.regist(userPO);
		if (isSuccess) {
			System.out.println("保存成功");
		}
	}

}
