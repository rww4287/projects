package com.spring.test.vo;

import java.util.List;

import com.spring.hashtag.vo.HashTagVO;

public class MovieVO {

	private String movieId;
	private String movieName;
	private int movieYear;
	private String movieDirector;
	private String moviePoster;
	private String hashTag;
	private List<HashTagVO> hashTagList;
	private String movieWriter;
	
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public int getMovieYear() {
		return movieYear;
	}
	public void setMovieYear(int movieYear) {
		this.movieYear = movieYear;
	}
	public String getMovieDirector() {
		return movieDirector;
	}
	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}
	public String getMoviePoster() {
		return moviePoster;
	}
	public void setMoviePoster(String moviePoster) {
		this.moviePoster = moviePoster;
	}
	public String getHashTag() {
		return hashTag;
	}
	public void setHashTag(String hashTag) {
		this.hashTag = hashTag;
	}
	public List<HashTagVO> getHashTagList() {
		return hashTagList;
	}
	public void setHashTagList(List<HashTagVO> hashTagList) {
		this.hashTagList = hashTagList;
	}
	public String getMovieWriter() {
		return movieWriter;
	}
	public void setMovieWriter(String movieWriter) {
		this.movieWriter = movieWriter;
	}

	
	
}
