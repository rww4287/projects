package com.spring.user.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.spring.user.vo.UserVO;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

	@Override
	public int insertOneUser(UserVO userVO) {
		return getSqlSession().insert("UserDao.insertOneUser", userVO);
	}

	@Override
	public UserVO selectOneUser(UserVO userVO) {
		return getSqlSession().selectOne("UserDao.selectOneUser", userVO);
	}

	@Override
	public String selectSaltByEmail(String userEmail) {

		return getSqlSession().selectOne("UserDao.selectSaltByEmail", userEmail);
	}

}
