package com.bookory.server.helper;

import java.sql.Date;
import java.util.List;

public class AccessTokenPayload {
    private Long userId;
    private String username;
    private List<String> authorities;
    private Date expirationDate;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<String> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public AccessTokenPayload() {
		super();
	}
	public AccessTokenPayload(Long userId, String username, List<String> authorities, Date expirationDate) {
		super();
		this.userId = userId;
		this.username = username;
		this.authorities = authorities;
		this.expirationDate = expirationDate;
	}
	@Override
	public String toString() {
		return "AccessTokenPayload [userId=" + userId + ", username=" + username + ", authorities=" + authorities
				+ ", expirationDate=" + expirationDate + "]";
	}
    

}