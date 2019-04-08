package com.mycompany.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.util.WebUtils;

import com.mycompany.mapper.UserMapper;
import com.mycompany.vo.User;

/**
 * Handles requests for the application home page.
 * 
 * @SessionAttributes("")
 *  ㄴ Session Scope에서 관리할 ModelAttribute이름.
 *  ㄴ SessionAttributes("loginId") 설정시 Model에 ("loginId") 이라는 이름으로
 *    저장되는 데이터가 있다면 그 데이터를 세션(HttpSession)에도 자동으로 저장한다.
 * 
 */

@Controller
@SessionAttributes("loginInfo")
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
	
	
	@RequestMapping(value="/" , method=GET)
	public String main() throws Exception{
		return "redirect:/login";
	}
	
	//처음로그인시 메인화면으로 전달
	@RequestMapping(value="/login", method=GET)
	public String loginMain() {
		return "user/login";
	}
	
	/**
	 * 로그인성공시 메인화면으로 이동
	 * 기본 MVC에서는 여기서 session등록을 했을 것이다.
	 * Model Class - 스프링 MVC에서 제공하는 데이터 전달용 객체
	 * 과거 Servlet에서는 RequestDispatcher에 데이터를 저장했듯이, 스프링에서는 Model을 이용하여 데이터를 저장.
	 * 
	 * 사용자가 '자동로그인'을 선택한 경우 필요한 기능을 추가한다.
	 * 
	 * 반환형이 return이므로 view 설정에 따라 간다. 이 경우 /views/loginPost.jsp를 찾게 됨.
	 */
	
	@RequestMapping(value="/user/login", method=POST)
	public String loginPost(@ModelAttribute User user, HttpSession session, Model model) {
		logger.info("/user/login  POST enter");
		logger.info("user infomation >> " + user.toString());
		
		//DB에 저장할 세션값을 만들어준다.
		//System.out.println("세션값 출력 : " + session.getId());
		User userVO = userMapper.getLoginInfo(user);
		//System.out.println("userVO.toString() >> " + userVO.toString());

		if(userVO !=null) { //로그인 정보가 맞다면..
			//DB에 자동로그인을 위한 loginInfo를 저장한다.
			// HttpSession에는 값이 없고 loginCookie에 값을 있을 경우 로그인 이력이 있기 때문에, 이 정보를 가져오기 위함임.
			
			String sessionkey = session.getId();
			
			//@@SessionAttributes 에 의해 세션에 자동저장된다.
			model.addAttribute("loginInfo",userVO);
			
			if(user.isUseCookie()) {
				int amount = 60 * 60 * 24 * 7;
				Date sessionLimit = new Date(System.currentTimeMillis() + (1000*amount));
				int result = userMapper.keepLogin(userVO.getEmail(),sessionkey, sessionLimit);
				
				if(result==0) { logger.info("현재 세션의 ID값과 유효기간이 업데이트되지 않았습니다."); }
				else {
					logger.info("현재 세션의 ID값과 유효기간이 업데이트 되었습니다.");
				}
			}
			return "redirect:/bookCon/books";
		}else {
			return "redirect:/login";
		}
		
	}
	
	
	/** 로그아웃의 경우 session값을 없앤다.
	 *  주의) 어노테이션으로 정의된 세션을 사용하는 것은 일반적으로 HttpSession을 이용하지만, 세션의 상태를 관리하기 위해서는
	 *  스프링에서 제공하는 SessionStatus를 사용해야 한다.
	 *  
	 *  setComplete() - 세션종료 메소드
	 */
	@RequestMapping(value="/user/logout", method=GET)
	public String logOut(HttpServletRequest request, HttpServletResponse response, HttpSession session, SessionStatus status) {
		logger.info("logOut GET Enter");
		
		//바로 status.setComplet()으로 해제도 되지만, 기존방식과도 섞어서 HttpSession으로 처리함.
		User sessionName = (User) session.getAttribute("loginInfo");
		
		if(sessionName!=null) {
			logger.info("로그아웃 session is not null");
			status.setComplete(); 
			
			//쿠키도 있다면 같이 지워줘야 한다
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			if(loginCookie!=null) {
				logger.info("저장된 쿠키가 삭제합니다.");
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				//DB에서도 삭제
				userMapper.keepLogin(sessionName.getEmail(),session.getId(), new Date());
			}
			
			//로그아웃 결과값 체크
			if(status.isComplete()==true) {
				logger.info("로그아웃 Session remove success... ");
			}else {
				logger.info("Session remove Faile... ");
			}
		}else {
			logger.info("로그아웃 session is null");
		}
		return "redirect:/login";
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
