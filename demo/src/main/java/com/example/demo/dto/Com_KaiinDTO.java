package com.example.demo.dto;

import java.util.Date;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Com_KaiinDTO {
	
	private String userID;
	private String userPW;
	private String userAddress;
	private Date createDate;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPW() {
		return userPW;
	}
	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Com_KaiinDTO() {
		
	}
	
	public Com_KaiinDTO(String userID, String userPW, String userAddress,Date createDate) {
		this.userID = userID;
		this.userPW = userPW;
		this.userAddress = userAddress;
		this.createDate = createDate;
	}
}