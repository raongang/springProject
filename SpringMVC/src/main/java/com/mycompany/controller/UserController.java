package com.mycompany.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.mapper.UserMapper;
import com.mycompany.vo.User;

@Controller
public class UserController {
	
	@Autowired
	private UserMapper userMapper;
	
	private static final Logger logger= LoggerFactory.getLogger(UserController.class);

	/**	
	   WebDataBinder - 스프링을 베이스로 한 웹 애플리케이션에서 데이터 바인딩을 구현한 오브젝트
	   StringTrimmerEditor Class - 웹어플에서 입력란에 아무것도 입력하지 않고 요청할 경우 getParameter를 하면 null이 아닌 공백이 들어온다. 
	   이를 방지하기 위해서 사용함
	   StringTrimmerEditor 오브젝트는 String 오브젝트의 trim메소드의 결과로 변환해주는 PropertyEditor이지만,
	   생성자에 true를 넣으면 공백을 null로 변환해주므로 매우 유용
 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		logger.info("Init Binder enter!!");
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	//처음로그인시 메인화면으로 전달
	@RequestMapping(value="/login", method=GET)
	public String login() {
		return "user/login";
	}
	

	/**
	 * 
	   <pre>
	    1 내용 : signUp시 데이터가 없더라도 Model을 선언한 이유는 /user/signup jsp에서
	    <form:form modelAttribute="user" action="${signUpPath}" method="POST"> 의 modelAttribute가 선언되어 
	    있기 때문
	   </pre>
	   
	   2. 내용 :  Model
		- 스프링MVC가 준비하는 오브젝트(즉,스프링MVC에서 자동으로 만들어준다는 의미임)
		- 컨트롤러에서 뷰로 넘겨줄 오브젝트를 저장하기 위한 오브젝트
		- HttpServletRequest와 HttpSession처럼 String형 키와 오브젝트를 연결해서 오브젝트를 유지
			
		ModelAttribute
		 - 자동으로 해당 객체를 뷰까지 전달하도록 만드는 애노테이션
	 * 
	 *  @Author : raongang
	 *  @Date   : 2019. 3. 22.
	 */
	
	@RequestMapping(value="/signup", method=GET)
	public String signup(Model model) {
		User user = new User();
		System.out.println("/signup GET user >> " + user.toString());
		model.addAttribute("user",user);
		return "user/signup";
	}
	

	
	
	/* TEST용
	@ResponseBody
	@RequestMapping(value="/signup",method=POST)
	public String register(@ModelAttribute User user) {
		return user.toString();
	}*/
	
	@RequestMapping(value="/signup",method=POST)
	public String register(@ModelAttribute User user) {
		userMapper.insertUser(user);
		return "redirect:/login";
	}
	
	
}
