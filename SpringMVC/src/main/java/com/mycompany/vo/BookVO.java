package com.mycompany.vo;

import javax.validation.constraints.NotNull;


public class BookVO {
	int id;
	String title;
	
	@NotNull //테스트용
	String author;
	
	String image;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		 return "Book [id=" + id + ", title=" + title + ", author=" + author + ", image=" + image + "]";
	}
	
}
