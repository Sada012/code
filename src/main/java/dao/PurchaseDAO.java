package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Purchase;
import util.DateTime;

public class PurchaseDAO {

	private static String RDB_DRIVE = "org.mariadb.jdbc.Driver";
	private static String URL = "jdbc:mariadb://localhost/feemadb";
	private static String USER = "root";
	private static String PASSWD = "root123";
	
	public Purchase selectByPurchase_id(String purchase_id) {

		/* 変数の宣言 */
		Connection con = null;
		Statement smt = null;
		Purchase purchaseInfo = new Purchase();

		try {
			// DBに接続
			con = getConnection();
			smt = con.createStatement();

			// SQL文
			String sql = "SELECT * FROM purchase_info WHERE purchase_id ='" + purchase_id + "'";

			// SQL文発行
			ResultSet rs = smt.executeQuery(sql);

			// 必要なユーザー情報の取得
			while (rs.next()) {
				purchaseInfo.setPurchase_id(Integer.parseInt(rs.getString("purchase_id")));
				purchaseInfo.setUser_id(Integer.parseInt(rs.getString("user_id")));
				purchaseInfo.setPurchase_day(rs.getString("purchase_day"));
				purchaseInfo.setTrading_status(Integer.parseInt(rs.getString("trading_status")));
				purchaseInfo.setDeposit_status(Integer.parseInt(rs.getString("deposit_status")));
				purchaseInfo.setPayment_method(Integer.parseInt(rs.getString("payment_method")));
				purchaseInfo.setMerchandise_id(Integer.parseInt(rs.getString("merchandise_id")));
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return purchaseInfo;
	}
	
	
	
	
	

	public void insert(Purchase purchase) {

		Connection con = null;
		Statement smt = null;
		DateTime time = new DateTime();

		try {
			// SQL文 
			
			String sql = "INSERT INTO purchase_info VALUES('','"
					+ purchase.getMerchandise_id() + "','"
					+ purchase.getUser_id() + "','"
					+ time.Time() + "','"
					+ purchase.getTrading_status() + "','"
					+ purchase.getDeposit_status() + "','"
					+ purchase.getPayment_method()
					+ "')";
			
			//getConnectionメソッドの呼び出し 
			con = getConnection();

			//createStatementメソッドの呼び出し 
			smt = con.createStatement();

			// SQL文発行
			smt.executeUpdate(sql);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			// リソースの解放 
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}

	/* 全ての購入情報の一覧 */
	public ArrayList<Purchase> selectAll() {

		Connection con = null;
		Statement smt = null;

		//AllayListオブジェクトを生成
		ArrayList<Purchase> purchaseList = new ArrayList<Purchase>();

		//SQL文を文字列として定義
		String sql = "SELECT * FROM purchase_info ORDER BY user_id";

		try {

			//Connectionオブジェクトを生成
			con = getConnection();

			//Statementオブジェクトを生成
			smt = con.createStatement();

			//SQLをDBへ発行
			ResultSet rs = smt.executeQuery(sql);

			//検索件数分すべて取り出して格納
			while (rs.next()) {
				Purchase purchase = new Purchase();
				purchase.setPurchase_id(rs.getInt("purchase_id"));
				purchase.setUser_id(rs.getInt("user_id"));
				purchase.setPurchase_day(rs.getString("purchase_day"));
				purchase.setTrading_status(rs.getInt("trading_status"));
				purchase.setDeposit_status(rs.getInt("deposit_status"));
				purchase.setPayment_method(rs.getInt("payment_method"));
				purchase.setMerchandise_id(rs.getInt("merchandise_id"));

				purchaseList.add(purchase);
			}

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			//Statementオブジェクトをクローズ
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			//Connectionオブジェクトをクローズ
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return purchaseList;
	}

	public Purchase selectByPurchase(String merchandise_id) {

		/* 変数の宣言 */
		Connection con = null;
		Statement smt = null;
		Purchase purchaseInfo = new Purchase();

		try {
			// DBに接続
			con = getConnection();
			smt = con.createStatement();

			// SQL文
			String sql = "SELECT * FROM purchase_info WHERE merchandise_id ='" + merchandise_id + "'";

			// SQL文発行
			ResultSet rs = smt.executeQuery(sql);

			// 必要なユーザー情報の取得
			while (rs.next()) {
				purchaseInfo.setPurchase_id(Integer.parseInt(rs.getString("purchase_id")));
				purchaseInfo.setUser_id(Integer.parseInt(rs.getString("user_id")));
				purchaseInfo.setPurchase_day(rs.getString("purchase_day"));
				purchaseInfo.setTrading_status(Integer.parseInt(rs.getString("trading_status")));
				purchaseInfo.setDeposit_status(Integer.parseInt(rs.getString("deposit_status")));
				purchaseInfo.setPayment_method(Integer.parseInt(rs.getString("payment_method")));
				purchaseInfo.setMerchandise_id(Integer.parseInt(rs.getString("merchandise_id")));
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return purchaseInfo;
	}

	public ArrayList<Purchase> userSelectByPurchase(String user_id) {

		/* 変数の宣言 */
		Connection con = null;
		Statement smt = null;
		//ArrayListオブジェクトを生成
		ArrayList<Purchase> purchaseUser = new ArrayList<Purchase>();

		try {
			// DBに接続
			con = getConnection();
			smt = con.createStatement();

			// SQL文
			String sql = "SELECT * FROM purchase_info WHERE user_id ='" + user_id + "'";

			// SQL文発行
			ResultSet rs = smt.executeQuery(sql);

			// 必要なユーザー情報の取得
			while (rs.next()) {
				Purchase purchase = new Purchase();
				purchase.setPurchase_id(Integer.parseInt(rs.getString("purchase_id")));
				purchase.setUser_id(Integer.parseInt(rs.getString("user_id")));
				purchase.setPurchase_day(rs.getString("purchase_day"));
				purchase.setTrading_status(Integer.parseInt(rs.getString("trading_status")));
				purchase.setDeposit_status(Integer.parseInt(rs.getString("deposit_status")));
				purchase.setPayment_method(Integer.parseInt(rs.getString("payment_method")));
				purchase.setMerchandise_id(Integer.parseInt(rs.getString("Merchandise_id")));

				purchaseUser.add(purchase);

			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
		return purchaseUser;
	}

	/* ユーザー情報の更新 */
	public void situationUpdate(Purchase purchase) {

		/* 変数の宣言 */
		Connection con = null;
		Statement smt = null;

		try {
			String sql = "";
			//DBに接続
			con = getConnection();
			smt = con.createStatement();

			if(purchase.getTrading_status() == 2) {
				//SQL文
				sql = "UPDATE purchase_info SET trading_status='" + purchase.getTrading_status() + "' WHERE purchase_id='" + purchase.getPurchase_id() + "'";
			} else if(purchase.getDeposit_status() == 1) {
				//SQL文
				sql = "UPDATE purchase_info SET deposit_status='" + purchase.getDeposit_status() + "' WHERE purchase_id='" + purchase.getPurchase_id() + "'";
			}
			
			//SQL文発行
			smt.executeUpdate(sql);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}
	
	//データベース接続情報を利用してデータベースに接続するクラスメソッドgetConnection
	private static Connection getConnection() {
		try {
			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(URL, USER, PASSWD);
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

}