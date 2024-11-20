package com.example.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.config.SessionStorage;
import com.example.demo.dto.Com_ShohinDTO;

import jakarta.servlet.http.HttpServletRequest;

@Repository
public class Com_ShohinDAO {

	@Autowired
	private SessionStorage sessionStorage;
	
	private static String url = "jdbc:postgresql://localhost:5432/testdb?characterEncoding=UTF-8";
	private static String username = "testuser";
	private static String password = "testpass";
	private static String driverName = "org.postgresql.Driver";

	private static final String sqlInsertShohin = "insert into Com_Shohin (shohin_id,shohin_name,tanka,CREATEDATE,UPDATEDATE,part,img,delete_flg) values (?, ?, ?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?,?,false)";
	private static final String sqlSelectAllShohin = "select * from Com_Shohin order by shohin_id asc";
	private static final String sqlSelectShohin = "select * from Com_Shohin where shohin_id = ?";
	private static final String sqlUpdateShohin = "update Com_Shohin set shohin_name = ?, tanka = ?, updatedate = current_timestamp, part = ?, img = ? where shohin_id = ?";
	private static final String sqlDeleteShohin = "update Com_Shohin set delete_flg = ? where shohin_id = ?";
	private static final String sqlSelectAllShohinNotDeleted = "select * from Com_Shohin where delete_flg = false order by shohin_id asc";
	
	private static PreparedStatement pstmtInsert;
	private static PreparedStatement pstmtSelect;
	private static PreparedStatement pstmtSelectAll;
	private static PreparedStatement pstmtUpdate;
	private static PreparedStatement pstmtDelete;
	private static PreparedStatement pstmtSelectAllNotDeleted;
	private static Connection con = null;

	/**
	 * クラス名：connect 機能：データベースに接続する
	 */
	public static void connect() throws Exception {
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url, username, password);
			System.out.println(con);

			pstmtInsert = con.prepareStatement(sqlInsertShohin);
			pstmtSelectAll = con.prepareStatement(sqlSelectAllShohin);
			pstmtSelect = con.prepareStatement(sqlSelectShohin);
			pstmtUpdate = con.prepareStatement(sqlUpdateShohin);
			pstmtDelete = con.prepareStatement(sqlDeleteShohin);
			pstmtSelectAllNotDeleted = con.prepareStatement(sqlSelectAllShohinNotDeleted);
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
			if (pstmtSelectAllNotDeleted!=null)
				pstmtSelectAllNotDeleted.close();
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
	public static ArrayList<Com_ShohinDTO> getAllShohin() {
		ResultSet rs = null;
		ArrayList<Com_ShohinDTO> Shohins = new ArrayList<Com_ShohinDTO>();
		Com_ShohinDTO Shohin = null;
		try {
			connect();
			rs = pstmtSelectAll.executeQuery();
			while (rs.next()) {
				Shohin = new Com_ShohinDTO();
				Shohin.setShohin_id(rs.getInt("shohin_ID"));
				Shohin.setShohin_name(rs.getString("shohin_Name"));
				Shohin.setTanka(rs.getDouble("tanka"));
				Shohin.setCreatedate(rs.getTimestamp("createDate"));
				Shohin.setUpdatedate(rs.getTimestamp("updatedate"));
				Shohin.setImg(rs.getString("img"));
				Shohin.setPart(rs.getInt("part"));
				Shohin.setDelete_flg(rs.getBoolean("delete_flg"));

				Shohins.add(Shohin);
			}
			return Shohins;
		} catch (Exception e) {
			System.out.println("エラーが発生しました。");
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Shohins;
	}

	
	public static ArrayList<Com_ShohinDTO> getAllShohinNotDeleted() {
		ResultSet rs = null;
		ArrayList<Com_ShohinDTO> Shohins = new ArrayList<Com_ShohinDTO>();
		Com_ShohinDTO Shohin = null;
		try {
			connect();
			rs = pstmtSelectAllNotDeleted.executeQuery();
			while (rs.next()) {
				Shohin = new Com_ShohinDTO();
				Shohin.setShohin_id(rs.getInt("shohin_ID"));
				Shohin.setShohin_name(rs.getString("shohin_Name"));
				Shohin.setTanka(rs.getDouble("tanka"));
				Shohin.setCreatedate(rs.getTimestamp("createDate"));
				Shohin.setUpdatedate(rs.getTimestamp("updatedate"));
				Shohin.setImg(rs.getString("img"));
				Shohin.setPart(rs.getInt("part"));
				Shohin.setDelete_flg(rs.getBoolean("delete_flg"));

				Shohins.add(Shohin);
			}
			return Shohins;
		} catch (Exception e) {
			System.out.println("エラーが発生しました。");
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Shohins;
	}
	
	/**
	 * クラス名：getShohin 機能：商品マスタの該当するデータを取得する
	 * 
	 * @param request リクエスト情報の取得
	 */
		
	
	public static Com_ShohinDTO getShousaiShohin(int shohin_id) {
		ResultSet rs = null;
		Com_ShohinDTO Shohin = new Com_ShohinDTO();
		try {
			connect();
			pstmtSelect.setInt(1, shohin_id);
			rs = pstmtSelect.executeQuery();
			while (rs.next()) {
				Shohin.setShohin_id(rs.getInt("shohin_id"));
				Shohin.setShohin_name(rs.getString("shohin_name"));
				Shohin.setTanka(rs.getDouble("tanka"));
				Shohin.setCreatedate(rs.getTimestamp("createDate"));
				Shohin.setUpdatedate(rs.getTimestamp("updatedate"));
				Shohin.setImg(rs.getString("img"));
				Shohin.setPart(rs.getInt("part"));
				Shohin.setDelete_flg(rs.getBoolean("delete_flg"));
			}
			System.out.println(Shohin);
			return Shohin;
		} catch (Exception e) {
			System.out.println("エラーが発生しました。");
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return Shohin;
	}

	/**
	 * クラス名：InsertShohin 機能：商品マスタ新規登録画面で入力されたデータを商品マスタに登録する
	 * 
	 * @param request リクエスト情報の取得
	 */
	public static void InsertShohin(Com_ShohinDTO shohinDTO) {
		if(checkID(shohinDTO)) {
			return;
		}
		try {
			connect();
			pstmtInsert.setInt(1, shohinDTO.getShohin_id());
			pstmtInsert.setString(2, shohinDTO.getShohin_name());
			pstmtInsert.setDouble(3, shohinDTO.getTanka());
			pstmtInsert.setInt(4, shohinDTO.getPart());
			pstmtInsert.setString(5, shohinDTO.getImg());
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
	public static void UpadteShohin(Com_ShohinDTO shohin) {
		try {
			connect();
			pstmtUpdate.setString(1, shohin.getShohin_name());
			pstmtUpdate.setDouble(2, shohin.getTanka());
			pstmtUpdate.setInt(3, shohin.getPart());
			pstmtUpdate.setString(4, shohin.getImg());
			pstmtUpdate.setInt(5, shohin.getShohin_id());

			if (pstmtUpdate.executeUpdate() == 1) {
				System.out.println("修正成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			disConnect();
		}

	}

	/**
	 * クラス名：deleteShohin 機能：商品マスタ詳細画面に表示されたデータを商品マスタから物理削除する
	 * 
	 * @param request リクエスト情報の取得
	 */
	public static Com_ShohinDTO deleteShohin(int shohinID) {
		Com_ShohinDTO shohinDTO = getShousaiShohin(shohinID);
		shohinDTO.setDelete_flg(!shohinDTO.isDelete_flg());
		try {
			connect();
			pstmtDelete.setBoolean(1,shohinDTO.isDelete_flg());
			pstmtDelete.setInt(2, shohinID);
			if (pstmtDelete.executeUpdate() == 1) {
				System.out.println("削除成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return shohinDTO;

	}

	/**
	 * クラス名：checkID 機能：商品マスタに商品マスタ一覧画面で選択されたIDが存在するかどうか？
	 * 
	 * @param request リクエスト情報の取得
	 */
	public static boolean checkID(Com_ShohinDTO ShohinDTO) {
		String sql = "SELECT shohin_id FROM Com_shohin WHERE shohin_ID = ?";
		try (Connection con = DriverManager.getConnection(url, username, password);
				PreparedStatement pstmtSelect = con.prepareStatement(sql)) {

			pstmtSelect.setInt(1, ShohinDTO.getShohin_id());
			try (ResultSet rs = pstmtSelect.executeQuery()) {
				if (rs.next()) {
					String shohinID = rs.getString("shohin_id");
					return shohinID != null && !shohinID.isBlank();
				}
			}
		} catch (Exception e) {
			System.out.println("エラーが発生しました。");
			e.printStackTrace();
		}
		return false;
	}
}
