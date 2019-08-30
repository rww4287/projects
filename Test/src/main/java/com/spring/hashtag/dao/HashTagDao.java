package com.spring.hashtag.dao;

import java.util.List;

import com.spring.hashtag.vo.HashTagVO;
import com.spring.hashtag.vo.PopularHashTagVO;

public interface HashTagDao {

	public int insertOneHashTag(HashTagVO hashTagVO);
	public List<HashTagVO> selectAllHashTagByMovieId(String movieId);
	public List<String> selectAllMovieIdByContent(String content);
	public List<PopularHashTagVO> selectPopularHashTags();
	public int deleteHashTagByMovieId(String movieId);

}
