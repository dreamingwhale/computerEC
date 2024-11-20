package com.example.demo.classes;

import java.util.Date;

import com.example.demo.dto.Com_Order_ListDTO;
import com.example.demo.dto.Com_Order_List_ProductDTO;

public class OrderedProducts {

	private int orderID;
	private String userid;
	private Date createdate;
	private int shohin_id;
	private Double tanka;
	private int part;
	private String img;
	
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public int getShohin_id() {
		return shohin_id;
	}
	public void setShohin_id(int shohin_id) {
		this.shohin_id = shohin_id;
	}
	public Double getTanka() {
		return tanka;
	}
	public void setTanka(Double tanka) {
		this.tanka = tanka;
	}
	public int getPart() {
		return part;
	}
	public void setPart(int part) {
		this.part = part;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
}
