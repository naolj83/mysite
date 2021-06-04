package com.douzone.mysite.web.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class UpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		userVo = new UserRepository().findByNo(userVo.getNo()); 
		
		request.setAttribute("name", userVo.getName());
		request.setAttribute("email", userVo.getEmail());
		request.setAttribute("gender", userVo.getGender());
		
		MvcUtils.forward("user/updateform", request, response);
	}

}
