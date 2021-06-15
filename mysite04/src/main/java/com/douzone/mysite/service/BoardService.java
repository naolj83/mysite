package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	BoardRepository boardRepository;
	
	@Transactional
	public void addMessage(BoardVo vo) {
		boardRepository.insert(vo);
	}
	
	public void deleteMessage(Long no) {
		boardRepository.delete(no);
	}
	
	public List<BoardVo> getMessageList(){
		return boardRepository.findAll();
	}
	
	public void updateMessage(BoardVo boarvo) {
		boardRepository.update(boarvo);
	}
	
	public void updateHitMessage(Long boardNo) {
		boardRepository.updateHit(boardNo);
	}
	
	public BoardVo getFindByNo(Long no) {
		return boardRepository.findByNo(no);
	}
	
	public int findMaxGroupNo() {
		return boardRepository.findMaxGroupNo();
	}
	
	public void updateReply(BoardVo boardVo) {
		boardRepository.updateReply(boardVo.getGroupNo(), boardVo.getOrderNo());	// orderno 밀기(부모에서 +1)
		boardVo.setOrderNo(boardVo.getOrderNo() + 1); // +1시켜주고 insert
		boardVo.setDepth(boardVo.getDepth() + 1);
		boardRepository.insert(boardVo);
	}
	

}
