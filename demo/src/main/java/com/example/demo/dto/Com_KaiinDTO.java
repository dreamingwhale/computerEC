package com.example.demo.dto;

import java.sql.Timestamp;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Com_KaiinDTO {
	
	private String userID;
	private String userPW;
	private String userAddress;
	private Timestamp createDate;
	
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
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
	public Com_KaiinDTO() {
		
	}
	
	public Com_KaiinDTO(String userID, String userPW, String userAddress,Timestamp createDate) {
		this.userID = userID;
		this.userPW = userPW;
		this.userAddress = userAddress;
		this.createDate = createDate;
	}
}