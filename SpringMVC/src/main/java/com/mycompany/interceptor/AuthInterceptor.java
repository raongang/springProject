package com.mycompany.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.mycompany.mapper.UserMapper;
import com.mycompany.vo.User;

public class AuthInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
	
	@Autowired
	private UserMapper userMapper;
	
	
	/**
      2019-04-06 raongang
      - 로그인이 필요한 페이지에 대한 인터셉터 처리 
      - 현재 사용자의 세션에 login 이 존재하지 않지만, 쿠키중에 loginCookie가 존재할때 처리 추가.
      
      - 리턴값이 boolean인데 이 값에 따라서 다음 인터셉터나 대상 컨트롤러를 호출할 것인지를 결정.
    */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		
		logger.info("AuthInterceptor prehandle enter");

		/**
		 * 2019-04-04 raongang  여기가 왜 에러 나는지 의문
		 *  - jquery-ui.js or jquery-ui.css만 하면 여기서 에러 난다.
		 *  - 다른 버전으로 해도 동일. 원인이 뭐지.......ㅠㅠ
		*/
		HandlerMethod method = (HandlerMethod)handler;
		Method methodObj = method.getMethod();
		logger.info("AuthInterceptor pre handle method name >> " + methodObj.getName());

		HttpSession session = request.getSession();
		
		//현재 로그인이 대한 세션정보가 있는지를 확인한다.
		if(session.getAttribute("loginInfo")==null) {
			logger.info("AuthInterceptor prehandle enter 현재 사용자는 미로그인 상태입니다.");
			
			//로그인에 대한 세션정보가 없다면 cookie에 저장된 session값이 있는지를 조회한다.
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			
			//쿠키가 있다면 로그인 기록이 있는 사용자이기 때문에 저장된 사용자 정보를 가져온다.
			if(loginCookie!=null) {
				//System.out.println("loginCookie.getValue >> " + loginCookie.getValue());	
				User userVO = userMapper.checkLoginBefore(loginCookie.getValue());
				
				//사용자 정보를 다시 저장한다.
				if(userVO!=null) {
					logger.info("쿠키검색 결과 >> " + userVO.toString());
					session.setAttribute("loginInfo",userVO);
					return true;
				}
				
			}else {
				System.out.println("쿠키값 널");
				//로그인하지 않은 사용자가 다른 페이지 접근시 로그인으로 이동
				response.sendRedirect("/login");
				return false;
			}
		}else {
			logger.info("AuthInterceptor prehandle enter 현재 사용자는 로그인 상태입니다.");
		}
		return true;
	}//end prehandle
	
}
