package com.mycompany.vo;

import java.util.List;

public class CartVO {
	//장바구니 상태값 설정
	public static final int SHOPPING=0;
	public static final int ORDERED=1;
	public static final int SHIPPING=2;
	public static final int DELYVERED =3;
	
	private int id;
	private int status;
	private int user_id;
	
	private List<ItemVO> item;
	private int totalPrice;
	
	public CartVO() {
		
	}
	
	public CartVO(int status, int user_id) {
		this.status = status;
		this.user_id = user_id;
				
	}
	
	
	public List<ItemVO> getItem() {
		return item;
	}

	public void setItem(List<ItemVO> item) {
		this.item = item;
	}

	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
    @Override
    public String toString() {
        return "CartVO [id=" + id + ", status=" + status + ", user_id=" + user_id + "]";
    }	
}
