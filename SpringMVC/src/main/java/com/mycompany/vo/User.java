package com.mycompany.vo;

import java.util.Date;

public class User {
	
	int id;
	String email;
	String password;
	boolean useCookie; //자동 로그인 기능
	
	//자동로그인을 위한 세션 관리 변수
	String sessionkey;
	Date sessionLimit;
	
	
	public Date getSessionLimit() {
		return sessionLimit;
	}
	public void setSessionLimit(Date sessionLimit) {
		this.sessionLimit = sessionLimit;
	}
	public String getSessionkey() {
		return sessionkey;
	}
	public void setSessionkey(String sessionkey) {
		this.sessionkey = sessionkey;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isUseCookie() {
		return useCookie;
	}
	public void setUseCookie(boolean useCookie) {
		this.useCookie = useCookie;
	}
	@Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", password=" + password +  ", sessionkey= " + sessionkey + ", sessionLimit= "+ sessionLimit +"]";
    }	
	
}
