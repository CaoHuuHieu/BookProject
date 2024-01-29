package com.bookory.models.request;

import java.util.List;

public class OrderRequestDTO {
	private List<OrderStoreDTO> stores;
	private OrderRecipient recipient;
	private int payment;
	public List<OrderStoreDTO> getStores() {
		return stores;
	}
	public void setStores(List<OrderStoreDTO> stores) {
		this.stores = stores;
	}
	public OrderRecipient getRecipient() {
		return recipient;
	}
	public void setRecipient(OrderRecipient recipient) {
		this.recipient = recipient;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}

	

}
