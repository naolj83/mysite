package com.douzone.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("")
	public String index() {	//뷰(jsp)이름이랑 핸들러이름이랑(메서드) 통일시키는게 좋음 
		return "/WEB-INF/views/main/index.jsp";
	}
}
