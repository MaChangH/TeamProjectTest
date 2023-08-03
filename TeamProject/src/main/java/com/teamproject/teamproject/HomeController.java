package com.teamproject.teamproject;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.teamproject.teamproject.member.MemberDAO;

@Controller
public class HomeController {
	
	@Autowired
	private MemberDAO mDAO;
	
	// 처음 접속 시 요청 페이지
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest req) {
		mDAO.loginCheck(req);
		req.setAttribute("cp", "home.jsp");
		return "index";
	}
	
	// HOME버튼 클릭시 요청 페이지
	@RequestMapping(value = "/home.go", method = RequestMethod.GET)
	public String goHome(HttpServletRequest req) {
		mDAO.loginCheck(req);
		req.setAttribute("cp", "home.jsp");
		return "index";
	}
	
	// MEMBER버튼 클릭시 요청 페이지
		@RequestMapping(value = "/member.go", method = RequestMethod.GET)
		public String goMember(HttpServletRequest req) {
			mDAO.loginCheck(req);
			req.setAttribute("cp", "teamMember.jsp");
			return "index";
		}
}
