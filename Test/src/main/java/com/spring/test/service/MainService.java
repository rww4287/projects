package com.spring.test.service;

import java.util.HashMap;
import java.util.List;

import com.spring.hashtag.vo.PopularHashTagVO;
import com.spring.like.vo.LikeVO;
import com.spring.test.vo.MovieSearchVO;
import com.spring.test.vo.MovieVO;
import com.spring.test.vo.ReplyVO;

public interface MainService {
	
	public List<MovieVO> getAllMoiveList(MovieSearchVO movieSearchVO);
	public MovieVO getOneMovie(String movieId);
	public boolean removeMovie(String movieId);
	public String addMovie(MovieVO movieVO);
	public boolean modifyMovie(MovieVO movieVO);
	public boolean addReply(ReplyVO replyVO);
	public List<ReplyVO> getRepliesByMovieId(String movieId);
	public boolean deleteOneReply(String replyId);
	public List<PopularHashTagVO> getPopularHashTags();
	public boolean addOneLike(LikeVO likeVO);
	public boolean removeOneLike(LikeVO likeVO);
	public LikeVO getLikeCheckByLikeVO(LikeVO likeVO);
	public int getLikeCountByMovieId(String movieId);
}
