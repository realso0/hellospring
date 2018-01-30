package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Hello {

	@RequestMapping("/spring") //get,post 방식 둘다 받을 수 있음.
	public String hello() {
		System.out.println("/hellospring/spring");
		return "/WEB-INF/views/index.jsp"; //return은 jsp파일을 알려줌.
	}
	
}
