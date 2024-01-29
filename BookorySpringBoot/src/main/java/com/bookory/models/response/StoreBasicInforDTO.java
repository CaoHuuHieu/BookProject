package com.bookory.models.response;

public class StoreBasicInforDTO {
	private Long id;
	private String name;
	private String avatar;
	private AddressResponseDTO address;
	private long vnpayId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public AddressResponseDTO getAddress() {
		return address;
	}
	public void setAddress(AddressResponseDTO address) {
		this.address = address;
	}
	public long getVnpayId() {
		return vnpayId;
	}
	public void setVnpayId(long vnpayId) {
		this.vnpayId = vnpayId;
	}
	
	
	
}

