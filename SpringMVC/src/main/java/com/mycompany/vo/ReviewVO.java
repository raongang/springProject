package com.mycompany.vo;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;


public class ReviewVO {
	Integer id;
	
	@NotEmpty
	String text;
	@NotNull
	Integer bookId;
	Integer userId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "Review [id=" + id + ", text=" + text + ", bookId=" + bookId + ", userId=" + userId + "]";
	}
}
