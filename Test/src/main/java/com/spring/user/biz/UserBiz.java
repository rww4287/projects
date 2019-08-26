package com.spring.user.biz;
import com.spring.user.vo.UserVO;

public interface UserBiz {
	
	public boolean addOneUser(UserVO userVO);
	public UserVO getOneUser(UserVO userVO);
	public String getSaltByEmail(String userEmail);
}
