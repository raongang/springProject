package com.mycompany.vo;

public class User {
	
	int id;
	String email;
	String password;
	
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
	
    @Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", password=" + password + "]";
    }	
	
}
