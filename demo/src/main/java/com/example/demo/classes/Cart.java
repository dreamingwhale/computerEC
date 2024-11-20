package com.example.demo.classes;

import java.util.ArrayList;
import java.util.Date;

import com.example.demo.dao.Com_ShohinDAO;
import com.example.demo.dto.Com_ShohinDTO;

public class Cart {
	
	private Com_ShohinDTO shohinDTO;

	private int orderID;
	private String userID;
	
	
	private ArrayList <Cart>CartList=new ArrayList<Cart>();
	
	public Com_ShohinDTO getShohinDTO() {
		return shohinDTO;
	}

	public void setShohinDTO(Com_ShohinDTO shohinDTO) {
		this.shohinDTO = shohinDTO;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setShohinDTOByshohinID(int ShohinID) {
		Com_ShohinDAO shohinDAO = new Com_ShohinDAO();
		this.shohinDTO = shohinDAO.getShousaiShohin(ShohinID);
	}

	public ArrayList <Cart> getCartList() {
		return CartList;
	}

	public void setCartList(ArrayList <Cart> cartList) {
		CartList = cartList;
	}
	
}
