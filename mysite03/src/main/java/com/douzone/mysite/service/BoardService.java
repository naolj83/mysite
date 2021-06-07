package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	BoardRepository boardRepository;
	
	public void addMessage(BoardVo vo) {
		boardRepository.insert(vo);
	}
	
	public void deleteMessage(Long no) {
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		
		boardRepository.delete(vo);
	}
	
	public List<BoardVo> getMessageList(){
		return boardRepository.findAll();
	}
	
	public void updateMessage(BoardVo boarvo) {
		boardRepository.update(boarvo);
	}
	
	public void updateHitMessage(BoardVo vo) {
		boardRepository.updateHit(vo);
	}
	
	public BoardVo getFindByNo(Long no) {
		return boardRepository.findByNo(no);
	}
	

}
