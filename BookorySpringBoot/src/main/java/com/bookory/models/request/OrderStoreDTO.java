package com.bookory.models.request;

import java.util.List;

public class OrderStoreDTO {
	private long id;
	private String note;
	private List<Long> cartIds;
	private long totalMoney;
	public long transportFee;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public List<Long> getCartIds() {
		return cartIds;
	}
	public void setCartIds(List<Long> cartIds) {
		this.cartIds = cartIds;
	}
	public long getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(long totalMoney) {
		this.totalMoney = totalMoney;
	}
	public long getTransportFee() {
		return transportFee;
	}
	public void setTransportFee(long transportFee) {
		this.transportFee = transportFee;
	}
}
