package com.bookory.models.response;

import java.util.List;


public class OrderResponseDTO {
	private long id;
	private StoreBasicInforDTO store;
	private List<OrderDetailResponseDTO> orderDetails;
	private int payment;
	private int status;
	private long totalMoney;
	private long transportFee;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public StoreBasicInforDTO getStore() {
		return store;
	}
	public void setStore(StoreBasicInforDTO store) {
		this.store = store;
	}
	public List<OrderDetailResponseDTO> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetailResponseDTO> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
