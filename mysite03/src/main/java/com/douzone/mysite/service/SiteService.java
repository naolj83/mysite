package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.SiteRepository;
import com.douzone.mysite.vo.SiteVo;

@Service
public class SiteService {

	@Autowired
	SiteRepository siteRepository;
	
	public List<SiteVo> getMessageList(){
		return siteRepository.findAll();
	}
	
	public void addMessage(SiteVo vo) {
		siteRepository.insert(vo);
	}
	
	public void updateMessage(SiteVo vo) {
		siteRepository.update(vo);
	}
	
	
}
