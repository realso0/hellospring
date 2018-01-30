package com.javaex.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user") // 공통된 상위 경로 빼주기
public class UserController {

	// @RequestMapping(value="/list",method=RequestMethod.GET) //@RequestMapping은
	// 주소를 주는 작업.
	// public String list() { //http://localhost:8088/hellospring/user/list에 출력됨.
	// System.out.println("user/list"); //우리는 주소로 쳐서 요청했는데(들어갔는데), 이는 get방식이므로,
	// method=RequestMethod.POST는 실행 안됨.
	// return ("/WEB-INF/views/index.jsp");
	// }
	// 주소를 확인한뒤, 컨트롤러에서 특정 패키지안의 주소값만을 뒤져, new함.
	@RequestMapping("/list") // 주소지정, 컨트롤러는 spring-servlet에서 어느 패키지만 뒤질것인지 지정해두었음.
	public String list() {
		System.out.println("user/list");
		return ("index"); // modelandview에 리턴값을 담아 dispatcherServlet에 보냄.
	}

	@RequestMapping(value="/form", method=RequestMethod.GET) // 생략하면, get, post방식 요청 모두를 handlerMapping에 저장해둠.
	public String form() {
		System.out.println("user/form");
		return ("form");
	}

	@RequestMapping("/add")
	public String add(@RequestParam(value = "age") int age, @RequestParam(value = "name") String name) { // 옵션 없을시
																											// 'value='는
																											// 생략가능
		System.out.println("user/add");
		System.out.println("age= " + age);
		System.out.println("name= " + name);
		return ("index");
	}

	@RequestMapping("/add2")
	public String add2(@RequestParam(value = "age", required = false, defaultValue = "-1") int age, // 옵션이 긴 경우 이렇게 써준다.
			@RequestParam(value = "name") String name) {
		System.out.println("user/add2");
		System.out.println("age= " + age);
		System.out.println("name= " + name);
		return ("index");
	}

	@RequestMapping("/add3")
	public String add3(@ModelAttribute UserVo userVo) { //입력하지 않은 값에 대하여, null값이 안나오는 현상 알아봐야함.!!!!!!!
		System.out.println(userVo.toString());
		return ("index"); 
	}

	@RequestMapping("/view/{no}") // {no}의 위치는 반드시 경로의 끝일 필요는 없음.
	public String view(@PathVariable("no") int no) {
		System.out.println(no);
		return ("index");
	}

	@RequestMapping("/sp") // {no}의 위치는 반드시 경로의 끝일 필요는 없음.
	public String sp(HttpServletRequest request, HttpServletResponse response) { // dispatcherServlet으로 부터 요청문서,응답문서 모두
																					// 필요하다면 이렇게 쓸수도 있음
		System.out.println("user/sp");
		return ("index");
	}

	@RequestMapping(value="/model")
	public String model(Model model) { // model은 Controller값을 dispatcherServlet에 보내기 위한 틀임.
		String str = "model에서 보낸 문자열";
		model.addAttribute("str", str); // model이 값을 객체로 담음
		return "index"; // return은 view값
	}
	
	@RequestMapping(value="/join") //http://localhost:8088/hellospring/user/join 주소에 이렇게 치면 리다이렉트되어, model메소드의 index.jsp값이 출력됨.
	public String model( @ModelAttribute UserVo userVo) {

	        return "redirect:/user/model";
	}
}
