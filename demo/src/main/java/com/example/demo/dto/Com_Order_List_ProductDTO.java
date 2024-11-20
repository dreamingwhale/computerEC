package com.example.demo.dto;

import java.util.Date;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Com_Order_List_ProductDTO {
    private int orderId;
    private int shohin_id;
    private Date createdate;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getShohin_id() {
		return shohin_id;
	}
	public void setShohin_id(int shohin_id) {
		this.shohin_id = shohin_id;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
}
