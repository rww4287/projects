package com.spring.test.biz;

import java.util.List;

import com.spring.test.vo.MovieSearchVO;
import com.spring.test.vo.MovieVO;
import com.spring.test.vo.ReplyVO;

public interface MainBiz {

	public List<MovieVO> getAllMoiveList();
	public MovieVO getOneMovie(String movieId);
	public boolean deleteOneMovie(String movieId);
	public String insertOneMovie(MovieVO movieVO);
	public boolean updateOneMovie(MovieVO movieVO);
	public boolean insertOneReply(ReplyVO replyVO);
	public List<ReplyVO> getRepliesByMovieId(String movieId);
	public boolean deleteOneReply(String replyId);
}
