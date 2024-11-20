package com.example.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.Com_KaiinDTO;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Repository
public class Com_KaiinDAO {

	private static String url = "jdbc:postgresql://localhost:5432/testdb?characterEncoding=UTF-8";
	private static String username = "testuser";
	private static String password = "testpass";
	private static String driverName = "org.postgresql.Driver";

	private static final String sqlInsertKaiin = "insert into Com_Kaiin (userID,userPW,userAddress,CREATEDATE) values (?, ?, ?,CURRENT_TIMESTAMP)";
	private static final String sqlSelectAllKaiin = "select * from Com_Kaiin order by userID asc";
	private static final String sqlSelectKaiin = "select * from Com_Kaiin where userID = ?";
	private static final String sqlUpdateKaiin = "update Com_Kaiin set userAddress = ? where userID = ?";
	private static final String sqlDeleteKaiin = "delete from Com_Kaiin where userID = ?";

	private static PreparedStatement pstmtInsert;
	private static PreparedStatement pstmtSelect;
	private static PreparedStatement pstmtSelectAll;
	private static PreparedStatement pstmtUpdate;
	private static PreparedStatement pstmtDelete;
	private static Connection con = null;

	/**
	 * クラス名：connect 機能：データベースに接続する
	 */
	public static void connect() throws Exception {
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url, username, password);
			System.out.println(con);

			pstmtInsert = con.prepareStatement(sqlInsertKaiin);
			pstmtSelectAll = con.prepareStatement(sqlSelectAllKaiin);
			pstmtSelect = con.prepareStatement(sqlSelectKaiin);
			pstmtUpdate = con.prepareStatement(sqlUpdateKaiin);
			pstmtDelete = con.prepareStatement(sqlDeleteKaiin);
			System.out.println("接続成功！");

		} catch (Exception e) {
			System.out.println("error");
		}
	}

	/**
	 * クラス名：disConnect 機能：データベースを切断する
	 */
	public static void disConnect() {

		try {
			if (pstmtInsert != null)
				pstmtInsert.close();
			if (pstmtSelectAll != null)
				pstmtSelectAll.close();
			if (pstmtSelect != null)
				pstmtSelect.close();
			if (pstmtUpdate != null)
				pstmtUpdate.close();
			if (pstmtDelete != null)
				pstmtDelete.close();
			if (con != null)
				con.close();
		} catch (Exception e) {

		}

	}

	/**
	 * クラス名：getAllShohin 機能：商品マスタの全てのデータを取得する
	 * 
	 * @param request リクエスト情報の取得
	 */
	public static void getAllKaiin(HttpServletRequest request) {
		ResultSet rs = null;
		ArrayList<Com_KaiinDTO> Kaiins = new ArrayList<Com_KaiinDTO>();
		Com_KaiinDTO Kaiin = null;
		try {
			connect();
			rs = pstmtSelectAll.executeQuery();
			while (rs.next()) {
				Kaiin = new Com_KaiinDTO();
				Kaiin.setUserID(rs.getString("userID"));
				Kaiin.setUserPW(rs.getString("userPW"));
				Kaiin.setUserAddress(rs.getString("userAddress"));
				Kaiin.setCreateDate(rs.getTimestamp("createDate"));

				Kaiins.add(Kaiin);

			}
			request.setAttribute("Kaiins", Kaiins);
			System.out.println(Kaiins);
		} catch (Exception e) {
			System.out.println("エラーが発生しました。");
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	/**
	 * クラス名：getShohin 機能：商品マスタの該当するデータを取得する
	 * 
	 * @param request リクエスト情報の取得
	 */
	public static Com_KaiinDTO getKaiin(Com_KaiinDTO kaiinDTO) {
		ResultSet rs = null;
		try {
			connect();
			pstmtSelect.setString(1, kaiinDTO.getUserID());
			rs = pstmtSelect.executeQuery();
			while (rs.next()) {
				kaiinDTO = new Com_KaiinDTO();
				kaiinDTO.setUserID(rs.getString("userID"));
				kaiinDTO.setUserPW(rs.getString("userPW"));
				kaiinDTO.setUserAddress(rs.getString("userAddress"));
				kaiinDTO.setCreateDate(rs.getTimestamp("createdate"));
			}

			return kaiinDTO;
		} catch (Exception e) {
			System.out.println("エラーが発生しました。");
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return kaiinDTO;
	}

	/**
	 * クラス名：InsertShohin 機能：商品マスタ新規登録画面で入力されたデータを商品マスタに登録する
	 * 
	 * @param request リクエスト情報の取得
	 */
	public static void InsertKaiin(Com_KaiinDTO kaiinDTO) {
		try {
			connect();
			pstmtInsert.setString(1, kaiinDTO.getUserID());
			pstmtInsert.setString(2, kaiinDTO.getUserPW());
			pstmtInsert.setString(3, kaiinDTO.getUserAddress());
			if (pstmtInsert.executeUpdate() == 1) {
				System.out.println("登録成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	/**
	 * クラス名：UpadteShohin 機能：商品マスタ詳細画面で入力されたデータを商品マスタに更新する
	 * 
	 * @param request リクエスト情報の取得
	 */
	public static void UpadteKaiin(HttpServletRequest request) {
		try {
			connect();
			pstmtUpdate.setString(1, request.getParameter("userID"));
			pstmtUpdate.setString(2, request.getParameter("userPW"));
			pstmtUpdate.setString(3, request.getParameter("userAddress"));

			if (pstmtUpdate.executeUpdate() == 1) {
				System.out.println("修正成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(request.getParameter("userID"));
			System.out.println(request.getParameter("userPW"));
			System.out.println(request.getParameter("userAddress"));

		} finally {
			disConnect();
		}

	}

	/**
	 * クラス名：deleteShohin 機能：商品マスタ詳細画面に表示されたデータを商品マスタから物理削除する
	 * 
	 * @param request リクエスト情報の取得
	 */
	public static void deleteKaiin(HttpServletRequest request) {
		try {
			connect();
			pstmtDelete.setString(1, request.getParameter("userID"));
			if (pstmtDelete.executeUpdate() == 1) {
				System.out.println("削除成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(request.getParameter("userID"));
		} finally {
			disConnect();
		}

	}

	/**
	 * クラス名：checkID 機能：商品マスタに商品マスタ一覧画面で選択されたIDが存在するかどうか？
	 * 
	 * @param request リクエスト情報の取得
	 */
	public static boolean checkID(Com_KaiinDTO kaiinDTO) {
		String sql = "SELECT userID FROM Com_Kaiin WHERE userID = ?";
		try (Connection con = DriverManager.getConnection(url, username, password);
				PreparedStatement pstmtSelect = con.prepareStatement(sql)) {

			pstmtSelect.setString(1, kaiinDTO.getUserID());
			try (ResultSet rs = pstmtSelect.executeQuery()) {
				// userID가 존재하는지 확인
				if (rs.next()) {
					String userID = rs.getString("userID");
					// userID가 null이 아니고 비어 있지 않은 경우 true 반환
					return userID != null && !userID.isBlank();
				}
			}
		} catch (Exception e) {
			System.out.println("エラーが発生しました。");
			e.printStackTrace();
		}
		return false;
	}

	public static boolean checkIDAndPW(Com_KaiinDTO kaiinDTO) {
		if (checkID(kaiinDTO)) {
			String sql = "SELECT userPW FROM Com_Kaiin WHERE userID = ?";
			try (Connection con = DriverManager.getConnection(url, username, password);
					PreparedStatement pstmtSelect = con.prepareStatement(sql)) {

				pstmtSelect.setString(1, kaiinDTO.getUserID());
				try (ResultSet rs = pstmtSelect.executeQuery()) {
					// userID가 존재하는지 확인
					if (rs.next()) {
						String userPWInDB = rs.getString("userPW");
						return kaiinDTO.getUserPW().equals(userPWInDB);
					}
				}
			} catch (Exception e) {
				System.out.println("エラーが発生しました。");
				e.printStackTrace();
			}
			return false;
		}
		return false;
	}
}
