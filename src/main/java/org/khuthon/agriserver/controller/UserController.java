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


@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/login")
	public String login(String loginId, String loginPw, HttpSession session, HttpServletRequest request){
		if((boolean)(request.getAttribute("isLogined"))) {
			return "already logined";
		}
		
		if(Util.isEmpty(loginId)) {
			return "no login id";
		}
		if(Util.isEmpty(loginPw)) {
			return "no login pw";
		}
        int id = userService.login(loginId, loginPw);
		if(id != 0){
            request.getSession().setAttribute("loginedId", (Integer)id);
            return "successfully login";
        }
        else {
            return "fail to login";
        }
	}
	
	@GetMapping("/logout")
	String logout(HttpSession session, HttpServletRequest request){
		if(!(boolean)(request.getAttribute("isLogined"))) {
			return "not logined";
		}

		request.getSession().setAttribute("loginedId",  null);
		request.getSession().setAttribute("isLogined",  false);
		return "successfully logout";
        
	}
	
	@PostMapping("/join")
	String join(HttpServletRequest request, @RequestBody PostRequest postRequest){
		if((boolean)(request.getAttribute("isLogined"))) {
			return "logined";
		}
		
		if(Util.isEmpty(postRequest.loginId)) {
			return "no login id";
		}
		if(Util.isEmpty(postRequest.loginPw)) {
			return "no login pw";
		}
		if(Util.isEmpty(postRequest.nickname)) {
			return "no nickname";
		}	
		
		if(userService.join(postRequest.loginId, postRequest.loginPw, postRequest.nickname)) {
            return "successfully joined";
        }
        else {
            return "fail to join";
        }
	}
    
    class PostRequest {
        String loginId;
        String loginPw;
        String nickname;
        
    }
}
