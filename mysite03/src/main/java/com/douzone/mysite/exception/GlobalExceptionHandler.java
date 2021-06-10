package com.douzone.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice	// Exception advisor에 advice로 하나로 묶음
public class GlobalExceptionHandler {
	
	private static final Log LOGGER = LogFactory.getLog(GlobalExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)	// Exception.class => 모든 예외
	public String handlerException(
			HttpServletRequest request,
			HttpServletResponse response,
			Exception e) throws Exception {
		// 1. 로깅(logging)
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		//System.out.println(errors);
		/**
		 * 1. appender
		 * 	  - file appender /log-mysite/exception.log
		 * 	  10M (Archiving 정책)
		 * 	  1-10 (rolling)
		 * 	  - console appender
		 * 
		 * 2. logger - com.douzone.mysite.exception, level(error), (console+file) appender
		 * 	  logger - Root, level(debug) console appender
		 */
		LOGGER.error(errors.toString());

		// 2. 요청 구분
		// 만약, JSON 요청인 경우이면, request header의 Accept에 application/json
		// 만약, HTML 요청인 경우이면, request header의 Accept에 text/html
		String accept = request.getHeader("accept");
		
		if(accept.matches(".*application/json.*")) {
			// 3. json 응답
			
		} else {
			// 3. 사과 페이지 가기(정상 종료)
			request.setAttribute("exception", errors.toString());
			request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);
		}
		// 3. 정상종료
		return "error/exception";
		
	}
}
