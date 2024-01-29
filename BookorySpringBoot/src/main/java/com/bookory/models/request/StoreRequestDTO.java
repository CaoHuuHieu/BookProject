package com.bookory.models.request;

import java.sql.Date;

public class StoreRequestDTO {
	private long   userId;
	private String name;
	private String phone;
	private String email;
	private String avatar;
	private String coverImage;
	private AddressRequestDTO address;
	private Date   createDate;
	private Date   updateDate;
	private Date   endDate;
	private long vnpayId;
	private int    status;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}
	
	public AddressRequestDTO getAddress() {
		return address;
	}
	public void setAddress(AddressRequestDTO address) {
		this.address = address;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public long getVnpayId() {
		return vnpayId;
	}
	public void setVnpayId(long vnpayId) {
		this.vnpayId = vnpayId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
