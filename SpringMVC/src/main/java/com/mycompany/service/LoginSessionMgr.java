package com.mycompany.service;

import javax.servlet.http.HttpServletRequest;

import com.mycompany.vo.User;

/**
 * 
   <pre>
    com.mycompany.service / LoginSessionMgr.java
    1. 설명 : 로그인 정보에 대한 세션관리 클래스
              필요시 여기를 이용한다.
   </pre>
 * @author raongang
 * @Date   2019. 3. 22.
 */
public class LoginSessionMgr {
	
	//로그인 정보 보존의 세션ID
	public static final String LOGIN_SESSION_ID="login_session_info";
	
	//로그인 정보를 세션에 SET
	public static void setLoginInfo(HttpServletRequest request, User user) {
		request.getSession().setAttribute(LOGIN_SESSION_ID, user);
	}
	
	//로그인 정보를 세션에 SET
	public static User getLoginInfo(HttpServletRequest request) {
		if(request.getSession().getAttribute(LOGIN_SESSION_ID)!=null) {
			return (User)request.getSession().getAttribute(LOGIN_SESSION_ID);
		}
		return null;
	}
	
}
