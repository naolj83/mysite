package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.GalleryService;
import com.douzone.mysite.vo.GalleryVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping("")
	public String index(Model model) {
		List<GalleryVo> list = galleryService.findAll();
		model.addAttribute("list", list);
		return "/gallery/index";
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String upload(GalleryVo galleryVo) {
		galleryService.insert(galleryVo);
		System.out.println(galleryVo);
		return "/gallery/upload";
	}
	
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable(value="no") Long no) {
		galleryService.delete(no);
		System.out.println(no);
		return "redirect:/gallery";
	}
	


}
