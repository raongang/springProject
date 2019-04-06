package com.mycompany.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
   <pre>
    com.mycompany.interceptor / LoginInterceptor.java
    1. 설명 : 인터셉터(Interceptor) 
   </pre>
   1-1. preHandle - 지정된 컨트롤러의 동작이전에 가로채는 역할을 한다
   1-2. postHandle - 지정된 컨트롤러의 동작이후에 처리. SpringMVC의 Front Controller의 DispatcherServlet이 화면을 처리하기 전에 동작한다.
   1-3. afterCompletion - DispatcherServlet의 화면처리가 완료된 상태에서 처리한다.
 * @author raongang
 * @Date   2019. 4. 4.
 */

public class LoginInterceptor extends HandlerInterceptorAdapter{

		private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
		private static final String LOGIN="loginId";
		
		/*	지정된 컨트롤러의 동작이전에 가로챈다.
    	리턴 타입 : 다음 인터셉터나 대상 컨트롤러를 호출하도록 할 것인지를 결정한다.
  		Object handler : 현재 실행할려는 메소드자체
	   */
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
			// TODO Auto-generated method stub
			
			logger.info("LoginInterceptor prehandle enter");
			

			/**
			 * 2019-04-04 raongang  여기가 왜 에러 나는지 의문
			 *  - jquery-ui.js or jquery-ui.css만 하면 여기서 에러 난다.
			 *  - 다른 버전으로 해도 동일. 원인이 뭐지.......ㅠㅠ
			 */
			//HandlerMethod method = (HandlerMethod)handler;
			//Method methodObj = method.getMethod();

			
			//기존에 로그인된 아이디가 있으면 삭제한다.
			HttpSession session = request.getSession();
			
			if(session.getAttribute(LOGIN)!=null) {
				// SessionStatus 을 여기서 사용할 수 없기 때문에, HttpSession을 이용한다.
				logger.info("login id arrive in session");
				session.removeAttribute(LOGIN);
			}else {
				logger.info("login id not arrive in session");
			}
			return true;
		}
		
		/**
		 *  자동 로그인과 쿠키기능 추가.
		 *   - 쿠키는 보안적으로 취약하지만 적극적으로 활용하게 된 계기는 모바일에서 매번 로그인하기가 힘들다는 문제에 대한 대안으로 사용되면서부터이다.
		 *   따라서, 로그인한 후 쿠키를 만들어서 브라우저로 전송하고, 다시 서버에 접속할 때 쿠키가 전달되는지 확인하는 소스를 추가한다.
		 */
		
		@Override
		public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
			// TODO Auto-generated method stub
			logger.info("LoginInterceptor postHandle enter");
			
			super.postHandle(request, response, handler, modelAndView);
		}
		 
}
