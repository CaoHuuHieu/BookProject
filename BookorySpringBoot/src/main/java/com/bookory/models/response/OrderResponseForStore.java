package com.bookory.models.response;

import java.sql.Date;
import java.util.List;


public class OrderResponseForStore {
	private long id;
	private String name;
	private String phone;
	private String address;
	private Date createDate;
	private int payment;
	private int status;
	private List<OrderDetailResponseDTO> orderDetails;
	private long totalMoney;
	private long transportFee;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	public List<OrderDetailResponseDTO> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetailResponseDTO> orderDetails) {
		this.orderDetails = orderDetails;
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
