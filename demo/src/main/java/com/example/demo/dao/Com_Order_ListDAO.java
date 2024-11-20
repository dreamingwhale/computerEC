package com.example.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.classes.Cart;
import com.example.demo.classes.OrderedProducts;
import com.example.demo.config.SessionStorage;
import com.example.demo.dto.Com_KaiinDTO;
import com.example.demo.dto.Com_Order_ListDTO;
import com.example.demo.dto.Com_Order_List_ProductDTO;
import com.example.demo.dto.Com_ShohinDTO;

import jakarta.servlet.http.HttpServletRequest;

public class Com_Order_ListDAO {

	@Autowired
	private SessionStorage sessionStorage;
	
	private static String url = "jdbc:postgresql://localhost:5432/testdb?characterEncoding=UTF-8";
	private static String username = "testuser";
	private static String password = "testpass";
	private static String driverName = "org.postgresql.Driver";
	
	private static final String sqlInsertOrderList = "insert into Com_order_List(orderId,userid, createdate) values(nextval('com_order_list_sequence'),?,current_timestamp)";
	private static final String sqlGetSequenceCurrentval = "Select Max(orderID) as currentOrderID from Com_Order_List";
	private static final String sqlInserOrderListProduct ="insert into Com_order_List_Product(orderid, shohin_id, createdate) values (?,?,current_timestamp)";
	private static final String sqlSelectOrderListByOrderID = "Select * from Com_order_List where orderid = ? asc";
	private static final String sqlSelectAllOrderedProduct = "Select Com_order_list.orderid, Com_order_list.userid, Com_order_list.createdate, Com_order_list_product.shohin_id, Com_Shohin.shohin_name, Com_Shohin.tanka, Com_Shohin.part, Com_Shohin.img from Com_order_list left join Com_order_list_Product on Com_order_list.orderId = Com_order_list_product.orderId left join com_shohin on Com_order_list_product.shohin_id = com_shohin.shohin_id where Com_order_list.orderid = ? order by Com_order_list.orderId asc";
	
	
	private static PreparedStatement pstmtInsert;
	private static PreparedStatement pstmtInsertProduct;
	private static PreparedStatement pstmtGetSequenceCurrentval;
	private static PreparedStatement pstmtSelect;
	private static PreparedStatement pstmtSelectOrderListByOrderID;
	private static PreparedStatement pstmtSelectAllOrderedProduct;
	private static Connection con = null;

	public static void connect() throws Exception {
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url, username, password);
			System.out.println(con);

			pstmtInsert = con.prepareStatement(sqlInsertOrderList);
			pstmtInsertProduct = con.prepareStatement(sqlInserOrderListProduct);
			pstmtGetSequenceCurrentval = con.prepareStatement(sqlGetSequenceCurrentval);
			pstmtSelectAllOrderedProduct = con.prepareStatement(sqlSelectAllOrderedProduct);
			System.out.println("接続成功！");

		} catch (Exception e) {
			System.out.println("error");
		}
	}
	
	
	public static void disConnect() {

		try {
			if (pstmtInsert != null)
				pstmtInsert.close();
			if (pstmtInsertProduct != null)
				pstmtInsertProduct.close();
			if (pstmtGetSequenceCurrentval != null)
				pstmtGetSequenceCurrentval.close();
			if (pstmtSelectAllOrderedProduct != null)
				pstmtSelectAllOrderedProduct.close();
			if (con != null)
				con.close();
		} catch (Exception e) {

		}

	}
	
	public static void InsertOrderList(String userID,HttpServletRequest request) {
		if(userID!=null) {
			try {
				connect();
				pstmtInsert.setString(1,userID);
				if (pstmtInsert.executeUpdate()==1) {
				    System.out.println("登録成功");
				    
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				disConnect();
			}
			
		}
		
	}
	
	public static int GetSequenceCurrentval() {
		ResultSet rs = null;
		try {
			connect();
			rs = pstmtGetSequenceCurrentval.executeQuery();
			while(rs.next()) {
				return rs.getInt("currentOrderID");				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	

	public static Com_Order_ListDTO sqlSelectOrderListByOrderID(String orderID) {
		ResultSet rs = null;
		Com_Order_ListDTO listDTO = new Com_Order_ListDTO();
		try {
			connect();
			pstmtSelect.setString(1, orderID);
			rs = pstmtSelect.executeQuery();
			while (rs.next()) {
				listDTO.setOrderId(rs.getInt("orderID"));
				listDTO.setUserId(rs.getString("userID"));
				listDTO.setCreatedate(rs.getTimestamp("creatdedate"));
			}

			return listDTO;
		} catch (Exception e) {
			System.out.println("エラーが発生しました。");
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return listDTO;
	}

	
	
	public static void InsertOrderListProduct(ArrayList<Cart> carts) {
		int currentOrderId = GetSequenceCurrentval();
		if(carts.size()==0) {
			System.out.println("InsertOrderListProductの方。shohinsが存在しない");
		}
		else {
			for(int i = 0;i<carts.size();i++) {
				try {
					connect();
					pstmtInsertProduct.setInt(1,currentOrderId);
					pstmtInsertProduct.setInt(2,carts.get(i).getOrderID());
					if (pstmtInsertProduct.executeUpdate() == 1) {
						
						System.out.println("登録成功！");
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					disConnect();
				}
			}
			
		}
		
	}
	
	public ArrayList<OrderedProducts> getOrderedProducts(){
		
		ResultSet rs = null;
		ArrayList<OrderedProducts> orderedProducts = new ArrayList<OrderedProducts>();
		int orderID = GetSequenceCurrentval();
		OrderedProducts orderedProduct = null;
		try {
			connect();
			pstmtSelectAllOrderedProduct.setInt(1, orderID);
			rs = pstmtSelectAllOrderedProduct.executeQuery();
			while (rs.next()) {
				
				orderedProduct = new OrderedProducts();
				orderedProduct.setOrderID(rs.getInt("orderID"));
				orderedProduct.setUserid(rs.getString("userid"));
				orderedProduct.setCreatedate(rs.getDate("createdate"));
				orderedProduct.setShohin_id(rs.getInt("shohin_id"));
				orderedProduct.setTanka(rs.getDouble("tanka"));
				orderedProduct.setPart(rs.getInt("part"));
				orderedProduct.setImg(rs.getString("img"));
				orderedProducts.add(orderedProduct);
			}
			return orderedProducts;
		} catch (Exception e) {
			System.out.println("エラーが発生しました。");
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return orderedProducts;
		
	}
	
}
