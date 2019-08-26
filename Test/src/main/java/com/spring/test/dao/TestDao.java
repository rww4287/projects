package com.spring.test.dao;

import java.util.List;


import com.spring.test.vo.MovieVO;
import com.spring.test.vo.ReplyVO;

public interface TestDao {

    public List<MovieVO> getAllMoiveList();
    public MovieVO getOneMovie(String movieId);
    public int deleteOneMovie(String movieId);
    public String insertOneMovie(MovieVO movieVO);
    public int updateOneMovie(MovieVO movieVO);
    public int insertOneReply(ReplyVO replyVO);
    public List<ReplyVO> getRepliesByMovieId(String movieId);
    public int deleteOneReply(String replyId);
}
