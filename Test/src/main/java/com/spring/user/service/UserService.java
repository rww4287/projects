package com.spring.user.service;

import com.spring.user.vo.UserVO;

public interface UserService {

	public boolean addOnerUser(UserVO userVO);
	public UserVO getOneUser(UserVO userVO);

}
