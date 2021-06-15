package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {
		return "user/join";
	}

	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute @Validated UserVo vo, BindingResult result, Model model) {
		if(result.hasErrors()) {
//			List<ObjectError> list = result.getAllErrors();		// ObjectError는 vo의 에러
//			for(ObjectError error : list) {
//			System.out.println(error);
//		}
	}
		//model.addAttribute("userVo", vo); - @ModelAttribute 어노테이션이 없을 때, spring은 코드 싫어하고 선언적인 거 좋아함.
		model.addAllAttributes(result.getModel());			// jsp에서 ${map.a }아니라 ${a } 이렇게만 써도 됨
		return "user/join";									// model이 jsp로 보내줌
		
		//userService.join(vo);
		//return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping(value="/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	/** Interceptor 전 로그인 처리
	 * 
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(
			HttpSession session,
			@RequestParam(value="email", required=true, defaultValue="") String email,
			@RequestParam(value="password", required=true, defaultValue="") String password,
			Model model
			) {
		UserVo authUser = userService.getUser(email, password);
		if(authUser == null) {
			model.addAttribute("result", "fail");
			model.addAttribute("email", email);
			return "user/login";
		}
		// 로그인 처리
		session.setAttribute("authUser", authUser);
		return "redirect:/";
	}
	*/
	
	/** Interceptor 전 logout 처리
	 * 
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// 접근제어
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		
		
		// 로그아웃 처리
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
	*/
	
	@Auth		// role="USER", test=false 가 default (Auth class)
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(@AuthUser UserVo authUser, Model model) {
		UserVo userVo = userService.getUser(authUser.getNo());
		model.addAttribute("user", userVo);
		return "user/update";
	}
	
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@AuthUser UserVo authUser, UserVo userVo) {  //(session에서 받아야 하는 값, 폼에서 넘어오는 값)
		userVo.setNo(authUser.getNo());
		userService.updateUser(userVo);
		authUser.setName(userVo.getName());
		
		return "redirect:/user/update";
	}
	
}
