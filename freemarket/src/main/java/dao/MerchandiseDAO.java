
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Merchandise;
import util.DateTime;

public class MerchandiseDAO {

	private static String RDB_DRIVE = "org.mariadb.jdbc.Driver";
	private static String URL = "jdbc:mariadb://localhost/feemadb";
	private static String USER = "root";
	private static String PASSWD = "root123";


	public int selectByPhoto(String photo) {
		Connection con = null;
		Statement smt = null;

		int id = 0;

		try {
			con = getConnection();
			smt = con.createStatement();

			//sql文の発行
			String sql = "SELECT * FROM merchandise_info WHERE photo='" + photo + "'";

			//検索結果をセット
			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				id = rs.getInt("merchandise_id");
			}

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
		return id;
	}

	public void update(Merchandise merchandise) {

		/* 変数の宣言 */
		Connection con = null;
		Statement smt = null;

		try {

			//DBに接続
			con = getConnection();
			smt = con.createStatement();

			String sql = null;

			if(merchandise.getPhoto() == null || merchandise.getPhoto().equals("")) {
				//SQL文
				sql = "UPDATE merchandise_info SET product_name='" + merchandise.getProduct_name() + "',price='" + merchandise.getPrice() +
						"',kinds='" + merchandise.getKinds() + "',remarks='" + merchandise.getRemarks() + "',sales_status='" + merchandise.getSales_status() + "' WHERE merchandise_id='"
						+ merchandise.getMerchandise_id() + "'";
			} else {
				//SQL文
				sql = "UPDATE merchandise_info SET product_name='" + merchandise.getProduct_name() + "',price='" + merchandise.getPrice() +
						"',kinds='" + merchandise.getKinds() + "',remarks='" + merchandise.getRemarks() + "',photo='" + merchandise.getPhoto() + "',sales_status='" + merchandise.getSales_status() + "' WHERE merchandise_id='"
						+ merchandise.getMerchandise_id() + "'";
			}


			//SQL文発行
			smt.executeUpdate(sql);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if( smt != null ){ 
				try{smt.close();}catch(SQLException ignore){} 
			} 
			if( con != null ){ 
				try{con.close();}catch(SQLException ignore){} 
			} 
		}
	}

	public ArrayList<Merchandise> selectAll(String sort, int user_id) {

		Connection con = null;
		Statement smt = null;

		// 検索した商品情報を格納するAllayListオブジェクトを生成  
		ArrayList<Merchandise> goods_List = new ArrayList<Merchandise>();

		try {

			/* SQL文 */
			String sql = null;
			if (sort.equals("ascending")) {
				sql = "SELECT * FROM merchandise_info WHERE user_id ='" + user_id + "'";
			} else if (sort.equals("descending")) {
				sql = "SELECT * FROM merchandise_info WHERE user_id ='" + user_id + "' ORDER BY listing_date DESC";
			}

			//getConnectionメソッドの呼び出し
			con = MerchandiseDAO.getConnection();
			//createStatementメソッドの呼び出し
			smt = con.createStatement();

			//SQL文を発行し結果セットを取得  
			ResultSet rs = smt.executeQuery(sql);

			// 検索結果をArrayListに格納
			while (rs.next()) {
				Merchandise merchandise = new Merchandise();
				merchandise.setMerchandise_id(rs.getInt("merchandise_id"));
				merchandise.setUser_id(rs.getInt("user_id"));
				merchandise.setProduct_name(rs.getString("product_name"));
				merchandise.setPrice(rs.getInt("price"));
				merchandise.setKinds(rs.getString("kinds"));
				merchandise.setRemarks(rs.getString("remarks"));
				merchandise.setExhibition_area(rs.getInt("exhibition_area"));
				merchandise.setSales_status(rs.getInt("sales_status"));
				merchandise.setPhoto(rs.getString("photo"));
				merchandise.setListing_date(rs.getString("listing_date"));

				//goods_Listにmerchandiseの値を追加 
				goods_List.add(merchandise);
			}
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
		return goods_List;
	}

	public Merchandise selectByMerchandise_id(String merchandise_id) {

		Connection con = null;
		Statement smt = null;

		Merchandise merchandise = new Merchandise();

		try {
			con = getConnection();
			smt = con.createStatement();

			//sql文の発行
			String sql = "SELECT * FROM merchandise_info WHERE merchandise_id='" + merchandise_id + "'";

			//検索結果をセット
			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				merchandise.setMerchandise_id(rs.getInt("merchandise_id"));
				merchandise.setUser_id(rs.getInt("user_id"));
				merchandise.setProduct_name(rs.getString("product_name"));
				merchandise.setPrice(rs.getInt("price"));
				merchandise.setKinds(rs.getString("kinds"));
				merchandise.setRemarks(rs.getString("remarks"));
				merchandise.setExhibition_area(rs.getInt("exhibition_area"));
				merchandise.setSales_status(rs.getInt("sales_status"));
				merchandise.setPhoto(rs.getString("photo"));
				merchandise.setListing_date(rs.getString("listing_date"));

			}
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
		return merchandise;
	}

	public void delete(String merchandise_id) {

		Connection con = null;
		Statement smt = null;

		try {

			String sql = "DELETE FROM merchandise_info WHERE merchandise_id = '" + merchandise_id + "'";

			con = getConnection();
			smt = con.createStatement();

			smt.executeUpdate(sql);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (smt != null) {
				try {
					smt.close(); //⑦statementオブジェクトをクローズします。
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close(); //⑧Connectionオブジェクトをクローズします。

				} catch (SQLException ignore) {
				}
			}
		}
	}

	public ArrayList<Merchandise> search(String product_name, String kinds, String remarks) {

		Connection con = null;
		Statement smt = null;
		//①検索した書籍情報を格納するArrayListオブジェクトを生成します。
		ArrayList<Merchandise> goods_List = new ArrayList<Merchandise>();
		try {
			//②引数の情報を利用し、検索用のSQL文を文字列として定義します。※SQL文は設計書参
			String sql =  "SELECT pruduct_name,kinds,remarks FROM merchandise_info " +
					"WHERE product_name LIKE '%" + product_name + "%' AND kinds LIKE '%" + kinds + "%' AND remarks LIKE '%" + remarks + "%'";

			//③BookDAOクラスに定義したgetConnection()メソッドを利用して、Connectionオブジェクトを生成します。
			con = MerchandiseDAO.getConnection();

			//④ConnectionオブジェクトのcreateStatement（）メソッドを利用して、Statementオブジェクトを生成します。
			smt = con.createStatement();

			//SQL文を発行し結果セットを取得
			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				Merchandise merchandise = new Merchandise();

				merchandise.setMerchandise_id(rs.getInt("merchandise_id"));
				merchandise.setUser_id(rs.getInt("user_id"));
				merchandise.setProduct_name(rs.getString("product_name"));
				merchandise.setPrice(rs.getInt("price"));
				merchandise.setKinds(rs.getString("kinds"));
				merchandise.setRemarks(rs.getString("remarks"));
				merchandise.setExhibition_area(rs.getInt("exhibition_area"));
				merchandise.setSales_status(rs.getInt("sales_status"));
				merchandise.setPhoto(rs.getString("photo"));
				merchandise.setListing_date(rs.getString("listing_date"));

				goods_List.add(merchandise);

			}
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
		return goods_List;
	}

	public ArrayList<Merchandise> selectAll(String sort) {

		Connection con = null;
		Statement smt = null;

		// 検索した商品情報を格納するAllayListオブジェクトを生成  
		ArrayList<Merchandise> goods_List = new ArrayList<Merchandise>();

		try {

			/* SQL文 */
			String sql = null;
			if (sort.equals("ascending")) {
				sql = "SELECT * FROM merchandise_info";
			} else if (sort.equals("descending")) {
				sql = "SELECT * FROM merchandise_info ORDER BY listing_date DESC";
			}

			//getConnectionメソッドの呼び出し
			con = MerchandiseDAO.getConnection();
			//createStatementメソッドの呼び出し
			smt = con.createStatement();

			//SQL文を発行し結果セットを取得  
			ResultSet rs = smt.executeQuery(sql);

			// 検索結果をArrayListに格納
			while (rs.next()) {
				Merchandise merchandise = new Merchandise();
				merchandise.setMerchandise_id(rs.getInt("merchandise_id"));
				merchandise.setUser_id(rs.getInt("user_id"));
				merchandise.setProduct_name(rs.getString("product_name"));
				merchandise.setPrice(rs.getInt("price"));
				merchandise.setKinds(rs.getString("kinds"));
				merchandise.setRemarks(rs.getString("remarks"));
				merchandise.setExhibition_area(rs.getInt("exhibition_area"));
				merchandise.setSales_status(rs.getInt("sales_status"));
				merchandise.setPhoto(rs.getString("photo"));
				merchandise.setListing_date(rs.getString("listing_date"));

				//goods_Listにmerchandiseの値を追加 
				goods_List.add(merchandise);

			}
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
		return goods_List;
	}

	public void insert(Merchandise merchandise) {

		Connection con = null;
		Statement smt = null;
		DateTime time = new DateTime();

		try {
			// SQL文 

			String sql = "INSERT INTO merchandise_info VALUES('','"
					+ merchandise.getUser_id() + "','"
					+ merchandise.getProduct_name() + "','"
					+ merchandise.getPrice() + "','"
					+ merchandise.getKinds() + "','"
					+ merchandise.getRemarks() + "','"
					+ merchandise.getExhibition_area() + "','"
					+ "1','"
					+ merchandise.getPhoto() + "','"
					+ time.Time()
					+ "')";

			//getConnectionメソッドの呼び出し 
			con = MerchandiseDAO.getConnection();

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

	public ArrayList<Merchandise> userSelectByMerchandise(String user_id) {

		Connection con = null;

		Statement smt = null;

		//検索した書籍情報を格納するAllayListオブジェクトを生成  

		ArrayList<Merchandise> goods_List = new ArrayList<Merchandise>();

		try {

			//SQL文を文字列として定義  

			String sql = "SELECT * FROM merchandise_info WHERE user_id ='" + user_id + "'";

			con = MerchandiseDAO.getConnection();

			smt = con.createStatement();

			//SQL文を発行し結果セットを取得  
			ResultSet rs = smt.executeQuery(sql);

			//AllayListオブジェクトにMerchandiseオブジェクトとして格納  
			while (rs.next()) {
				Merchandise merchandise = new Merchandise();
				merchandise.setMerchandise_id(rs.getInt("merchandise_id"));
				merchandise.setUser_id(rs.getInt("user_id"));
				merchandise.setProduct_name(rs.getString("product_name"));
				merchandise.setPrice(rs.getInt("price"));
				merchandise.setSales_status(rs.getInt("sales_status"));
				merchandise.setListing_date(rs.getString("listing_date"));
				//goods_Listにmerchandiseの値を追加 

				goods_List.add(merchandise);

			}

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

		return goods_List;

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