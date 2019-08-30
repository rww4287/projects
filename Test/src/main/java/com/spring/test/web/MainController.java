package com.spring.test.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.hashtag.biz.HashTagBiz;
import com.spring.hashtag.vo.HashTagVO;
import com.spring.hashtag.vo.PopularHashTagVO;
import com.spring.like.vo.LikeVO;
import com.spring.test.service.MainService;
import com.spring.test.vo.MovieSearchVO;
import com.spring.test.vo.MovieVO;
import com.spring.test.vo.ReplyVO;
import com.spring.user.vo.UserVO;
import com.google.gson.Gson;


@Controller
public class MainController {

	private MainService mainService;
	private JavaMailSender mailSender;
	
	
	public void setMainService(MainService mainService) {
		this.mainService = mainService;
	}
	
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	@ModelAttribute("popularHashTagList")
	public List<PopularHashTagVO> referencepopularHashTagList() {

		List<PopularHashTagVO> popularHashTagList = mainService.getPopularHashTags();

		return popularHashTagList;

	}

	
	@RequestMapping(value = "/mail/send", method= RequestMethod.GET)
	public ModelAndView viewMailSending() {
		ModelAndView view = new ModelAndView();

		view.setViewName("mail/write");
		return view;
	}

	@RequestMapping(value = "/mail/send", method= RequestMethod.POST)
	public void doMailSending(HttpServletRequest request, HttpServletResponse response) {
		
		String setfrom = "rww4287@gmail.com"; // 생략 하면 작동 하지 않음.
		String tomail = "kjj@qntech.co.kr"; // 받는 사람 이메일 
		String title = request.getParameter("title"); // 제목 
		
		String storeName = "매장이름 : " + request.getParameter("storeName") + "\n";// 매장이름 
		String position = "매장위치 : " + request.getParameter("position") + "\n";// 매장 위치  
		
		String phone = "연락처 : " + request.getParameter("phone") + "\n"; //연락처 
		String email = "이메일 : " + request.getParameter("email")+ "\n\n";  // 이메일 
		
		
		String info = storeName + position + phone + email;
		String content = request.getParameter("content"); // 내용 
		content = info + content;

		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			
			messageHelper.setFrom(setfrom); // 보내는 사람 이메일 (생략 하면 에러) 
			messageHelper.setTo(tomail); // 받는 사람 이메일 
			messageHelper.setSubject(title); // 메일 제목(생략 가능) 

			messageHelper.setText(content); // 메일 내용
			
			mailSender.send(message);
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('메일 전송이 완료 되었습니다!'); window.location.href = \"http://localhost:8080/test/movie\"; </script>");
			out.flush();
			out.close();
			
			//return "redirect:/movie";
			
		} catch (MessagingException e) {
			response.setContentType("text/html; charset=UTF-8");
			try {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('메일전송에 실패 하였습니다'); </script>");
				out.flush();
				out.close();
				
				//return "redirect:/mail/send";
			} catch (IOException e1) {
		
				e1.printStackTrace();
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		//return "redirect:/movie";
	}

	@RequestMapping(value = "/movie")
	public ModelAndView viewListPage(HttpServletRequest request) {

		MovieSearchVO movieSearchVO = new MovieSearchVO();
		movieSearchVO.setKeyword(request.getParameter("keyword"));
		
		List<MovieVO> movieList = mainService.getAllMoiveList(movieSearchVO);
		
		ModelAndView view = new ModelAndView();
	
		view.addObject("movieList",movieList);
		view.setViewName("movie/list");
		
		return view;
	}
	
	
	@RequestMapping(value = "/movie/detail/{movieId}")
	public ModelAndView viewDetailPage(@PathVariable String movieId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		UserVO user = (UserVO) session.getAttribute("_USER_");
		LikeVO likeVO = new LikeVO();
		likeVO.setMovieId(movieId);
		
		
		if(user != null) {
			likeVO.setUserEmail(user.getUserEmail());
		} else {
			likeVO.setUserEmail("");
		}

		MovieVO movie = mainService.getOneMovie(movieId);
	
		List<ReplyVO> replyList = mainService.getRepliesByMovieId(movieId);
		LikeVO like = mainService.getLikeCheckByLikeVO(likeVO);
		boolean likeCheck;
		int likeCount = mainService.getLikeCountByMovieId(movieId);
		
		ModelAndView view = new ModelAndView();
	
		if(like != null) {
			view.addObject("like", like);
			likeCheck = true;
			
		} else {
			likeCheck = false;
		}
		view.addObject("movie",movie);
		view.addObject("replyList",replyList);
		view.addObject("likeCheck",likeCheck);
		view.addObject("likeCount",likeCount);
		view.setViewName("movie/detail");
		
		return view;
	}
	
	@RequestMapping(value = "/movie/delete/{movieId}")
	public String doDeletePage(@PathVariable String movieId) {
		
		boolean isSuccess = mainService.removeMovie(movieId);
		
		if(isSuccess) {
			return "redirect:/movie";
		} else {
			return "redirect:/movie/detail" + movieId;
		}
	}
	
	@RequestMapping(value = "/movie/write", method= RequestMethod.GET)
	public String viewWritePage() {
		return "movie/write";
	}
	
	
	@RequestMapping(value = "/movie/write", method= RequestMethod.POST)
	public String doWritePage(MovieVO movieVO, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		UserVO user = (UserVO) session.getAttribute("_USER_");
		
		movieVO.setMovieWriter(user.getUserId());
		String movieId = mainService.addMovie(movieVO);
	
		if( movieId != null && movieId != "") {
			return "redirect:/movie";
		} else {
			return "redirect:/movie/write";
		}
	}
	
	
	@RequestMapping(value = "/movie/update/{movieId}", method= RequestMethod.GET)
	public ModelAndView viewUpdatePage(@PathVariable String movieId) {
		
		MovieVO movie = mainService.getOneMovie(movieId);
		ModelAndView view = new ModelAndView();
		
		view.addObject("movie",movie);
		view.setViewName("movie/update");
		
		return view;
	}
	
	@RequestMapping(value = "/movie/update", method= RequestMethod.POST)
	public String doUpdatePage(MovieVO movieVO) {
		
		boolean isSuccess = mainService.modifyMovie(movieVO);
		String movieId = movieVO.getMovieId();
		
		if(isSuccess) {
			return "redirect:/movie/detail/" + movieId;
			
		}else {
			return "redirect:/movie/update/" + movieId;
		}
		
	
	}
	
	@RequestMapping(value = "/movie/reply/write", method= RequestMethod.POST)
	@ResponseBody
	public String doWriteReply(HttpServletRequest req) {
		
		String writer = req.getParameter("writer");
		String content = req.getParameter("content");
		String movieId = req.getParameter("movieId");
		
		ReplyVO replyVO = new ReplyVO();
		
		replyVO.setReplyWriter(writer);
		replyVO.setReplyContent(content);
		replyVO.setMovieId(movieId);
		
		boolean isSuccess = mainService.addReply(replyVO);
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(isSuccess) {
		 	map.put("status","success");
		} else {
			map.put("status","fail");
		}
		Gson gson = new Gson();
		String json = gson.toJson(map);
		
		return json;
	}
	
	@RequestMapping(value = "/movie/reply/remove", method= RequestMethod.POST)
	@ResponseBody
	public String doDeleteReply(HttpServletRequest req) {
		
		String replyId = req.getParameter("replyId");
		
		boolean isSuccess = mainService.deleteOneReply(replyId);

		Map<String, Object> map = new HashMap<String, Object>();
		
		if(isSuccess) {
		 	map.put("status","success");
		} else {
			map.put("status","fail");
		}
		Gson gson = new Gson();
		String json = gson.toJson(map);
		
		return json;
	}
	
	@RequestMapping(value = "/movie/like/plus", method= RequestMethod.POST)
	@ResponseBody
	public String doLikePlus(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		UserVO user = (UserVO) session.getAttribute("_USER_");
		
		String movieId = req.getParameter("movieId");
		String userEmail = user.getUserEmail();
		
		
		LikeVO likeVO = new LikeVO();
		likeVO.setMovieId(movieId);
		likeVO.setUserEmail(userEmail);
		
		boolean isSuccess = mainService.addOneLike(likeVO);
		int likeCount = mainService.getLikeCountByMovieId(movieId);

		Map<String, Object> map = new HashMap<String, Object>();
		
		if(isSuccess) {
		 	map.put("status","success");
		 	map.put("likeCount", likeCount);
		} else {
			map.put("status","fail");
		}
		Gson gson = new Gson();
		String json = gson.toJson(map);
		
		return json;
	}
	
	@RequestMapping(value = "/movie/like/minus", method= RequestMethod.POST)
	@ResponseBody
	public String doLikeMinus(HttpServletRequest req) {

		HttpSession session = req.getSession();
		UserVO user = (UserVO) session.getAttribute("_USER_");
		
		String movieId = req.getParameter("movieId");
		String userEmail = user.getUserEmail();
		
		
		LikeVO likeVO = new LikeVO();
		
		likeVO.setMovieId(movieId);
		likeVO.setUserEmail(userEmail);
		
		boolean isSuccess = mainService.removeOneLike(likeVO);
		int likeCount = mainService.getLikeCountByMovieId(movieId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(isSuccess) {
		 	map.put("status","success");
		 	map.put("likeCount", likeCount);
		} else {
			map.put("status","fail");
		}
		Gson gson = new Gson();
		String json = gson.toJson(map);
		
		return json;
	}
	
	
}
