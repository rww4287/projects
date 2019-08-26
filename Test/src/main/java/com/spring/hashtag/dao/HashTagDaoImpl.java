package com.spring.hashtag.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.spring.hashtag.vo.HashTagVO;
import com.spring.hashtag.vo.PopularHashTagVO;

public class HashTagDaoImpl extends SqlSessionDaoSupport implements HashTagDao {

	@Override
	public int insertOneHashTag(HashTagVO hashTagVO) {
		return getSqlSession().insert("HashTagDao.insertOneHashTag", hashTagVO);
	}
	@Override
	public List<HashTagVO> selectAllHashTagByMovieId(String movieId) {
		return getSqlSession().selectList("HashTagDao.selectAllHashTagByMovieId", movieId);
	}

	@Override
	public List<String> selectAllMovieIdByContent(String content) {
		return getSqlSession().selectList("HashTagDao.selectAllMovieIdByContent", content);
	}

	@Override
	public List<PopularHashTagVO> selectPopularHashTags() {
		return getSqlSession().selectList("HashTagDao.selectPopularHashTags");
	}
	@Override
	public int deleteHashTagByMovieId(String movieId) {
		return getSqlSession().delete("HashTagDao.deleteHashTagByMovieId",movieId);
	}


	
}
