package com.mycompany.vo;

public class ItemVO {
	
	private int cart_id;
	private int book_id;
	private int amount;
	
	private BookVO book;

	
	//BookVO의 title변수가져오기 - jsp에서 사용하기 위함.
	public String getTitle() {
		return book.getTitle();
	}

	
	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public BookVO getBook() {
		return book;
	}

	public void setBook(BookVO book) {
		this.book = book;
	}
	
    @Override
    public String toString() {
        return "ItemVO [cart_id=" + cart_id + ", book_id=" + book_id + ", amount=" + amount + "]";
    }
	

}
