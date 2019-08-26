package com.spring.test.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.spring.hashtag.biz.HashTagBiz;
import com.spring.hashtag.vo.HashTagVO;
import com.spring.hashtag.vo.PopularHashTagVO;
import com.spring.test.biz.MainBiz;
import com.spring.test.vo.MovieSearchVO;
import com.spring.test.vo.MovieVO;
import com.spring.test.vo.ReplyVO;

public class MainServiceImpl implements MainService{

	private MainBiz mainBiz;
	private HashTagBiz hashTagBiz;
	
	public void setMainBiz(MainBiz mainBiz) {
		this.mainBiz= mainBiz;
		
	}
	public void setHashTagBiz(HashTagBiz hashTagBiz) {
		this.hashTagBiz = hashTagBiz;
	}
	
	@Override
	public List<MovieVO> getAllMoiveList(MovieSearchVO movieSearchVO) {
		
		  HashMap<String, List> map = new HashMap();
		  List<MovieVO> movieList = new ArrayList<MovieVO>();
		  List<MovieVO> resultList = new ArrayList<MovieVO>();
		  String content = movieSearchVO.getKeyword();
		  
		if (content == null || content == "") {
			
			movieList = mainBiz.getAllMoiveList();
			
	
			
		} else {
			
			List<String> movieIdList = hashTagBiz.getAllMovieIdByContent(content);
			int len = movieIdList.size();
			
			for(int i=0; i<len; i++) {
				MovieVO movie = mainBiz.getOneMovie(movieIdList.get(i));
		
				movieList.add(movie);
			}
			
		}
		
		for(int i=0; i<movieList.size(); i++) {
			MovieVO movieVO = movieList.get(i);
			List<HashTagVO> list = hashTagBiz.getAllHashTagByMovieId(movieVO.getMovieId());
			movieVO.setHashTagList(list);
			resultList.add(movieVO);
		}

		return resultList;
		
	}
	@Override
	public MovieVO getOneMovie(String movieId) {
		MovieVO movie = mainBiz.getOneMovie(movieId);

		List<HashTagVO> hashTagList = hashTagBiz.getAllHashTagByMovieId(movieId);
		movie.setHashTagList(hashTagList);
		
		return movie;
	}
	@Override
	public boolean removeMovie(String movieId) {
		if(mainBiz.deleteOneMovie(movieId)) {
			return hashTagBiz.removeOneHashTag(movieId);
		}
		else {
			return false;
		}
		 
	}
	@Override
	public String addMovie(MovieVO movieVO) {

		String movieId = mainBiz.insertOneMovie(movieVO);
		String hashTag = movieVO.getHashTag();
		String[] splitStr = hashTag.split("#");
		
		HashTagVO hashTagVO = null;
		List<HashTagVO> hashTagList = new ArrayList<HashTagVO>();
		
		for(int i=1; i<splitStr.length; i++){
			hashTagVO = new HashTagVO();
			hashTagVO.setContent(splitStr[i]);
			hashTagVO.setMovieId(movieId);
			hashTagBiz.addOneHashTag(hashTagVO);
			hashTagList.add(hashTagVO);
		}
		
		return movieId;
	}
	@Override
	public boolean modifyMovie(MovieVO movieVO) {

		return mainBiz.updateOneMovie(movieVO);
	}
	@Override
	public boolean addReply(ReplyVO replyVO) {

		return mainBiz.insertOneReply(replyVO);
	}
	@Override
	public List<ReplyVO> getRepliesByMovieId(String movieId) {
		
		return mainBiz.getRepliesByMovieId(movieId);
	}
	@Override
	public boolean deleteOneReply(String replyId) {

		return mainBiz.deleteOneReply(replyId);
	}
	@Override
	public List<PopularHashTagVO> getPopularHashTags() {
	
		return hashTagBiz.getPopularHashTags();
	}
	
	


}
