package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("")
	public String main(Model model) {
		List<BoardVo> list = boardService.getMessageList();
		model.addAttribute("list", list);
		return "board/list";		// forward 방식
	}
	
	@RequestMapping("/view/{no}")
	public String view(@PathVariable(value="no") String boardNo, Model model) {
		BoardVo vo = boardService.getFindByNo(Long.parseLong(boardNo));
		boardService.updateHitMessage(Long.parseLong(boardNo));
		model.addAttribute("board", vo);
		return "board/view";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String insert() {
		return "board/write";
	}
	
	@Auth
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(@AuthUser UserVo userVo, BoardVo vo) {
		vo.setUserNo(userVo.getNo());
		vo.setGroupNo(boardService.findMaxGroupNo());
		vo.setOrderNo(0);
		vo.setDepth(0);
		boardService.addMessage(vo);
		return "redirect:/board";
	}
	
	@Auth
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable(value="no") String boardNo) {
		boardService.deleteMessage(Long.parseLong(boardNo));
		return "redirect:/board";
	}
	
	@RequestMapping(value="/update/{no}", method=RequestMethod.GET)
	public String update(@PathVariable(value="no") String boardNo, Model model) {
		BoardVo boardVo = boardService.getFindByNo(Long.parseLong(boardNo));
		System.out.println(boardVo);
		model.addAttribute("board", boardVo);	// "board"라는 이름으로 boardVo데이터를 jsp로 넘겨줌
		return "board/modify";
	}
	
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(BoardVo boardVo) {
		boardService.updateMessage(boardVo);
		System.out.println(boardVo);
		return "redirect:/board";
	}

	@RequestMapping(value="/reply/{no}", method=RequestMethod.GET)
	public String replyform(@PathVariable(value="no") String boardNo, Model model) {
		BoardVo boardVo = boardService.getFindByNo(Long.parseLong(boardNo));
		System.out.println(boardVo);
		model.addAttribute("board", boardVo);	// "board"라는 이름으로 boardVo데이터를 jsp로 넘겨줌
		return "board/reply";
	}
	
	@RequestMapping(value="/reply", method=RequestMethod.POST)
	public String reply(@AuthUser UserVo userVo, BoardVo boardVo) {
		boardVo.setUserNo(userVo.getNo());
		boardService.updateReply(boardVo);
		return "redirect:/board";
	}
	
}
