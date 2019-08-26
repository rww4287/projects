package com.spring.user.biz;

import com.spring.user.dao.UserDao;
import com.spring.user.vo.UserVO;

public class UserBizImpl implements UserBiz {

	private UserDao userDao;
	
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean addOneUser(UserVO userVO) {
		return userDao.insertOneUser(userVO) > 0;
	}

	@Override
	public UserVO getOneUser(UserVO userVO) {
		return userDao.selectOneUser(userVO);
	}

	@Override
	public String getSaltByEmail(String userEmail) {

		return userDao.selectSaltByEmail(userEmail);
	}

}
