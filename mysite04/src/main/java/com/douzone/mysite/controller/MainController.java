package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.mysite.vo.UserVo;

@Controller
public class MainController {
	
	@Autowired
	SiteService siteService;
	
	@RequestMapping("")
	public String index(SiteVo siteVo, Model model) {	//뷰(jsp)이름이랑 핸들러이름이랑(메서드) 통일시키는게 좋음 
		siteVo = siteService.findAll();
		model.addAttribute("siteVo", siteVo);
		
		return "main/index";
	}
	@ResponseBody
	@RequestMapping("/hello")
	public String hello() {
		return "안녕~";
	}

	@ResponseBody
	@RequestMapping("/msg1")
	public String message1() {
		return "안녕~";
	}

	@ResponseBody
	@RequestMapping("/msg2")
	public Object message2() {
		UserVo vo = new UserVo();
		vo.setNo(1L);
		vo.setName("김나은");
		vo.setEmail("naolj83@gmail.com");
		return vo;
	}
}
