package org.lkw.user.Service;

import org.lkw.user.dao.UserDao;
import org.lkw.user.entity.UserPO;

public class UserService {

	/**
	 * 注册
	 * @return
	 */
	public boolean regist(UserPO userPO) {
		UserDao userDao = new UserDao();
		int sum = userDao.save(userPO);
		if (sum > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param accout
	 * @param password
	 * @return
	 */
	public UserPO login(String accout, String password) {
		UserDao userDao = new UserDao();
		return userDao.getByAccoutAndPwd(accout, password);
	}
	
	
}
