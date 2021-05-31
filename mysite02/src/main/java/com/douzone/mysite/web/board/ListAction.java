package com.douzone.mysite.web.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
//		totalPageNo = 
//		firstPageNo = 3
//		lastPageNo = 7
//		nextPageNo = 8
//		prevPageNo = 2
//		currentPageNo = 4
		
//		map = new.request
//		map.put("lastPageNo".lastPageNo)
//		
//		request.setAttribute("pageInfo", map);
		List<BoardVo> list = new BoardRepository().findAll();
		
		request.setAttribute("list", list);

		MvcUtils.forward("board/list", request, response);

	}

}