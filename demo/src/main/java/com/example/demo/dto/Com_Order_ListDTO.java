package com.example.demo.dto;

import java.util.Date;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Com_Order_ListDTO {
	
    private int orderId;
    private String userId;
    private Date createdate;
    
    public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
}
