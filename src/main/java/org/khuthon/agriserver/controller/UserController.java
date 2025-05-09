package org.khuthon.agriserver.controller;

import org.khuthon.agriserver.Util;
import org.khuthon.agriserver.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/login")
	public String login(String loginId,String loginPw, HttpSession session, HttpServletRequest request){
		if(!(request.getAttribute("isLogined") == null) && (boolean)(request.getAttribute("isLogined"))) {
            return "logined  <script>location.replace('/index.html')</script>";
        }
		
		if(Util.isEmpty(loginId)) {
			System.out.println("noid");
			return "no login id  <script>location.replace('/index.html')</script>";
		}
		if(Util.isEmpty(loginPw)) {
			System.out.println("nopw");
			return "no login pw  <script>location.replace('/index.html')</script>";
		}
        int id = userService.login(loginId, loginPw);
		if(id != 0){
			request.getSession().setAttribute("loginedId", (Integer)id);
			System.out.println("suc");
            return "successfully login <script>location.replace('/index.html')</script>";
        }
        else {
			System.out.println("fail");
            return "fail to login  <script>location.replace('/index.html')</script>";
        }
	}
	
	@GetMapping("/logout")
	String logout(HttpServletRequest request){
		if(request.getAttribute("isLogined") == null || !(boolean)(request.getAttribute("isLogined"))) {
            return "not logined";
        }

		request.getSession().setAttribute("loginedId",  null);
		request.getSession().setAttribute("isLogined",  false);
		return "successfully logout";
        
	}
	
	@PostMapping("/join")
	String join(HttpServletRequest request, String loginId,String loginPw,String nickname){
		if((boolean)(request.getAttribute("isLogined"))) {
			return "logined";
		}
		
		if(Util.isEmpty(loginId)) {
			return "no login id";
		}
		if(Util.isEmpty(loginPw)) {
			return "no login pw";
		}
		if(Util.isEmpty(nickname)) {
			return "no nickname";
		}	
		
		if(userService.join(loginId, loginPw, nickname)) {
            return "successfully joined";
        }
        else {
            return "fail to join";
        }
	}

	@GetMapping("/islogined")
	public boolean getMethodName(HttpServletRequest reqeust) {
		return (boolean)reqeust.getAttribute("isLogined");
	}
	
}
