package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;

@Controller
@RequestMapping("/site")
public class SiteController {

	@Autowired
	SiteService siteService;
	
	@RequestMapping("")
	public String index(Model model) {
		List<SiteVo> list = siteService.getMessageList();
		model.addAttribute("list", list);
		return "admin/main";
	}
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String add(SiteVo vo) {
		siteService.addMessage(vo);
		return "redirect:/admin";
	}
	
	public String update() {
		return "amdin/";
	}
}
