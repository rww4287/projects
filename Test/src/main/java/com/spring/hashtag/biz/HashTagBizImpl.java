package com.spring.hashtag.biz;

import java.util.List;

import com.spring.hashtag.dao.HashTagDao;
import com.spring.hashtag.vo.HashTagVO;
import com.spring.hashtag.vo.PopularHashTagVO;

public class HashTagBizImpl implements HashTagBiz{
	
	private HashTagDao hashTagDao;

	public void setHashTagDao(HashTagDao hashTagDao) {
		this.hashTagDao = hashTagDao;
	}
	@Override
	public boolean addOneHashTag(HashTagVO hashTagVO) {
		return hashTagDao.insertOneHashTag(hashTagVO) > 0;
	}
	@Override
	public List<HashTagVO> getAllHashTagByMovieId(String movieId) {
		return hashTagDao.selectAllHashTagByMovieId(movieId);
	}
	@Override
	public List<String> getAllMovieIdByContent(String content) {
		return hashTagDao.selectAllMovieIdByContent(content);
	}
	@Override
	public List<PopularHashTagVO> getPopularHashTags() {
		return hashTagDao.selectPopularHashTags();
	}
	@Override
	public boolean removeOneHashTag(String movieId) {
		return hashTagDao.deleteHashTagByMovieId(movieId) > 0;
	}

}
