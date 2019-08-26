package com.spring.test.dao;

import com.spring.test.vo.MovieVO;
import com.spring.test.vo.ReplyVO;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

public class TestDaoImpl extends SqlSessionDaoSupport implements TestDao {

	@Override
	public List<MovieVO> getAllMoiveList() {
		return getSqlSession().selectList("TestDao.getAllMoiveList");
	}

	@Override
	public MovieVO getOneMovie(String movieId) {
	
		return getSqlSession().selectOne("TestDao.getOneMovie",movieId);
	}

	@Override
	public int deleteOneMovie(String movieId) {
		return getSqlSession().delete("TestDao.deleteOneMovie",movieId);
	}

	@Override
	public String insertOneMovie(MovieVO movieVO) {
		int cnt = getSqlSession().insert("TestDao.insertOneMovie",movieVO);
		String id = (String)movieVO.getMovieId();
		return id;
	}

	@Override
	public int updateOneMovie(MovieVO movieVO) {
		return getSqlSession().update("TestDao.updateOneMovie", movieVO);
	}

	@Override
	public int insertOneReply(ReplyVO replyVO) {
		return getSqlSession().insert("TestDao.insertOneReply",replyVO);
	}

	@Override
	public List<ReplyVO> getRepliesByMovieId(String movieId) {
		return getSqlSession().selectList("TestDao.getReplyListByMovieId", movieId);
	}

	@Override
	public int deleteOneReply(String replyId) {
		return getSqlSession().delete("TestDao.deleteOneReply",replyId);
	}



}
