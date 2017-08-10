package com.ktds.movie.movie.vo;

import java.util.List;

public class MovieVO {

	 // 영화 대표코드
	private String movieId;
	// 영화 제목
	private String movieName;
	// 개봉일
	private String openDate; 
	// 박스오피스 순위
	private int rank; 
	// 누적관객수
	private String audiAcc;
	private String story;
	private String thumbnail;
	private List<String> actor;
	private List<String> genre;
	private String screeningGrade; // 상영 등급.
	private List<String> director;
	private String time;
	private String reservation;
	private String video;
	private String videoLink;
	
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
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getAudiAcc() {
		return audiAcc;
	}
	public void setAudiAcc(String audiAcc) {
		this.audiAcc = audiAcc;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public List<String> getActor() {
		return actor;
	}
	public void setActor(List<String> actor) {
		this.actor = actor;
	}
	public List<String> getGenre() {
		return genre;
	}
	public void setGenre(List<String> genre) {
		this.genre = genre;
	}
	public String getScreeningGrade() {
		return screeningGrade;
	}
	public void setScreeningGrade(String screeningGrade) {
		this.screeningGrade = screeningGrade;
	}
	public List<String> getDirector() {
		return director;
	}
	public void setDirector(List<String> director) {
		this.director = director;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getReservation() {
		return reservation;
	}
	public void setReservation(String reservation) {
		this.reservation = reservation;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public String getVideoLink() {
		return videoLink;
	}
	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}
	

}
