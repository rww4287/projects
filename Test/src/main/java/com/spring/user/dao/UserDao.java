package com.spring.user.dao;

import com.spring.user.vo.UserVO;

public interface UserDao {
	
	public int insertOneUser(UserVO userVO);
	public UserVO selectOneUser(UserVO userVO);
	public String selectSaltByEmail(String userEmail);
}
