package com.spring.hashtag.biz;
import java.util.List;

import com.spring.hashtag.vo.HashTagVO;
import com.spring.hashtag.vo.PopularHashTagVO;
public interface HashTagBiz {

	
	public boolean addOneHashTag(HashTagVO hashTagVO);
	public List<HashTagVO> getAllHashTagByMovieId(String movieId);
	public List<String> getAllMovieIdByContent(String content);
	public List<PopularHashTagVO> getPopularHashTags();
	public boolean removeOneHashTag(String movieId);
}
