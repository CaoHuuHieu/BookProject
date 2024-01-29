package com.bookory.models.request;

import java.util.List;

public class CartDetailForPay {
	private long userId;
	private List<Long> bookId;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public List<Long> getBookId() {
		return bookId;
	}
	public void setBookId(List<Long> bookId) {
		this.bookId = bookId;
	}
	
	

}
