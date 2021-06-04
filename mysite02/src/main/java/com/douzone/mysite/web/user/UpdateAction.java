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

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 접근제어(인증이 필요한 접근에 대한 체크)
		if(session == null) {
			MvcUtils.redirect(request.getContextPath(), request, response);
			return;
		}
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			MvcUtils.redirect(request.getContextPath(), request, response);
			return;
		}
		Long UserNo = authUser.getNo();
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		UserVo userVo = new UserVo();
		userVo.setNo(UserNo);
		userVo.setName(name);
		userVo.setPassword(password);
		userVo.setGender(gender);
		
		new UserRepository().update(userVo);
		
		MvcUtils.redirect(request.getContextPath(), request, response);
//		UserVo userVo = new UserRepository().findByNo(userNo);
//		request.setAttribute("userVo", userVo);

	}

}
