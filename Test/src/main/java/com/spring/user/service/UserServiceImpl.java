package com.spring.user.service;

import com.spring.common.utilities.SHA256Util;
import com.spring.user.biz.UserBiz;
import com.spring.user.vo.UserVO;

public class UserServiceImpl implements UserService {

	private UserBiz userBiz;
	
	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}

	@Override
	public boolean addOnerUser(UserVO userVO) {
		String salt = SHA256Util.generateSalt();
		userVO.setSalt(salt);
		
		String password = userVO.getUserPassword();
		password = SHA256Util.getEncrypt(password, salt);
		userVO.setUserPassword(password);
		
		return userBiz.addOneUser(userVO);
	}

	@Override
	public UserVO getOneUser(UserVO userVO) {
		String userEmail = userVO.getUserEmail();
		String salt = userBiz.getSaltByEmail(userEmail);
		
		String password = userVO.getUserPassword();
		password = SHA256Util.getEncrypt(password, salt);
		userVO.setUserPassword(password);
		UserVO user = userBiz.getOneUser(userVO);
//		
//		if (user != null) {
//			return user;
//		} else {
//			
//		}
		
		return user;
	}
	
	
}
