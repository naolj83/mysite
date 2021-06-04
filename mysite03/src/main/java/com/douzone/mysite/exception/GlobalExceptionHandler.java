package com.douzone.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice	// Exception advisor에 advice로 하나로 묶음
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)	// Exception.class => 모든 예외
	public String handlerException(Model model, Exception e) {
		//1. 로깅(logging)
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		System.out.println(errors);
		//2. 사과 페이지
		
		//3. 정상종료
		model.addAttribute("exception", errors.toString());
		return "error/exception";
		
	}
}
