package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.FileUploadService;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;

@Auth(role="ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private FileUploadService fileUploadService;

	@RequestMapping("")
	public String main(SiteVo siteVo, Model model) {
		siteVo = siteService.findAll();
		model.addAttribute("siteVo", siteVo);
		
		return "admin/main";
	}


	@RequestMapping("/guestbook")
	public String guesbook() {
		return "admin/guestbook";
	}

	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}

	@Auth
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
	
	
	@Auth
	@RequestMapping(value="/main/update", method=RequestMethod.POST)
	public String update(SiteVo siteVo, @RequestParam("file1") MultipartFile file ) {
		String url = fileUploadService.restore(file);
		siteVo.setProfile(url);
		
		siteService.update(siteVo);
		System.out.println(siteVo);
		return "redirect:/admin";
		
	}
}