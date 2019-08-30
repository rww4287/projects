package com.spring.user.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.spring.hashtag.vo.PopularHashTagVO;
import com.spring.test.service.MainService;
import com.spring.test.vo.MovieVO;
import com.spring.user.service.UserService;
import com.spring.user.vo.UserVO;

@Controller
public class UserController {

	private UserService userService;
	private MainService mainService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setMainService(MainService mainService) {
		this.mainService = mainService;
	}
	
	@ModelAttribute("popularHashTagList")
	public List<PopularHashTagVO> referencepopularHashTagList() {

		List<PopularHashTagVO> popularHashTagList = mainService.getPopularHashTags();

		return popularHashTagList;

	}
	
	@RequestMapping(value="/movie/user/signUp", method= RequestMethod.GET)
	public ModelAndView viewSignUpPage() {
		ModelAndView view = new ModelAndView();
		view.setViewName("user/signup");
		return view;
	}
	
	@RequestMapping(value="/movie/user/signUp", method= RequestMethod.POST)
	public void doSignUpPage (UserVO userVO, HttpServletResponse response) {
		
		if (userVO.getUserId() == "" || userVO.getUserEmail() == "" || userVO.getUserPassword() == "") {
			return;
		}

		boolean isSuccess = userService.addOnerUser(userVO);
		
		if(isSuccess) {
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('회원가입이 완료 되었습니다!'); window.location.href = \"http://localhost:8080/test/movie\"; </script>");
				out.flush();
				out.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('회원가입에 실패 하였습니다'); </script>");
				out.flush();
				out.close();
				response.sendRedirect("/test/movie/user/signUp");
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
	
	}
	
	@RequestMapping(value="/movie/user/login", method= RequestMethod.GET)
	public ModelAndView viewLoginPage() {
		ModelAndView view = new ModelAndView();
		
		view.setViewName("user/login");
		
		return view;
	}
	
	@RequestMapping(value="/movie/user/login", method= RequestMethod.POST)
	public void doLoginPage(UserVO userVO, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
	
		if (userVO.getUserId() == "" || userVO.getUserEmail() == "" || userVO.getUserPassword() == "") {
			return;
		}
	
		UserVO user = userService.getOneUser(userVO);
	
		if(user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("_USER_", user);

			PrintWriter out = response.getWriter();
			out.println("<script> window.location.href = \"http://localhost:8080/test/movie\"; </script>");
			out.flush();
			out.close();
		} else {
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('로그인에 실패 하였습니다! 아이디와 비밀번호를 확인해 주세요 '); window.location.href = \"http://localhost:8080/test/movie/user/login\"; </script>");
				out.flush();
				out.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
	
	}
	@RequestMapping(value="/movie/user/logout", method= RequestMethod.GET)
	public String doLogoutPage(HttpSession session) {
		session.invalidate();
		
		return "redirect:/movie";
	}
}
