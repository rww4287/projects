package com.spring.test.biz;

import java.util.List;

import com.spring.test.dao.TestDao;
import com.spring.test.vo.MovieSearchVO;
import com.spring.test.vo.MovieVO;
import com.spring.test.vo.ReplyVO;

public class MainBizImpl implements MainBiz{

	private TestDao testDao;
	
	
	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}


	@Override
	public List<MovieVO> getAllMoiveList() {
		List<MovieVO> movieList = testDao.getAllMoiveList();
		return movieList;
	}


	@Override
	public MovieVO getOneMovie(String movieId) {
		MovieVO movie = testDao.getOneMovie(movieId);
		return movie;
	}


	@Override
	public boolean deleteOneMovie(String movieId) {
		return testDao.deleteOneMovie(movieId) > 0;
	}


	@Override
	public String insertOneMovie(MovieVO movieVO) {
		return testDao.insertOneMovie(movieVO);
	}


	@Override
	public boolean updateOneMovie(MovieVO movieVO) {

		return testDao.updateOneMovie(movieVO) > 0;
	}


	@Override
	public boolean insertOneReply(ReplyVO replyVO) {
		return testDao.insertOneReply(replyVO) > 0;
	}


	@Override
	public List<ReplyVO> getRepliesByMovieId(String movieId) {
		return testDao.getRepliesByMovieId(movieId);
	}


	@Override
	public boolean deleteOneReply(String replyId) {
		return testDao.deleteOneReply(replyId) > 0;
	}


}
