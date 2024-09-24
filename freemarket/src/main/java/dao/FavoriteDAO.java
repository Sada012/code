package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Favorite;

public class FavoriteDAO {

	private static String RDB_DRIVE = "org.mariadb.jdbc.Driver";
	private static String URL = "jdbc:mariadb://localhost/feemadb";
	private static String USER = "root";
	private static String PASSWD = "root123";

	/* お気に入り登録 */
	public void insert(Favorite favorite) {

		/* 変数の宣言 */
		Connection con = null;
		Statement smt = null;

		try {
			// DBに接続 
			con = getConnection();
			smt = con.createStatement();

			//SQL文 
			String sql = "INSERT INTO favorite_info VALUES('" + favorite.getMerchandise_id() + "','"
					+ favorite.getUser_id() + "')";

			// SQL文発行 
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

	/* お気に入り削除 */
	public void delete(String merchandise_id) {

		Connection con = null;
		Statement smt = null;

		try {

			//①削除用のSQL文を文字列として定義
			String sql = "DELETE FROM favorite WHERE merchandise_id = '" + merchandise_id + "'";

			//②Connectionオブジェクトを生成
			con = getConnection();

			//③Statementオブジェクトを生成
			smt = con.createStatement();

			//④SQL文を発行し書籍データを登録
			smt.executeUpdate(sql);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			//⑤⑥StatementおよびConnectionオブジェクトをクローズ
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

	/* ユーザーのお気に入り一覧 */
	public ArrayList<Favorite> selectByuser_id(int user_id) {

		Connection con = null;
		Statement smt = null;

		Favorite favorite = new Favorite();
		ArrayList<Favorite> favorite_list = new ArrayList<Favorite>();

		try {
			String sql = "SELECT * FROM favorite_info WHERE user_id='" + user_id + "'";
			con = getConnection();
			smt = con.createStatement();

			ResultSet rs = smt.executeQuery(sql);

			while (rs.next()) {
				favorite = new Favorite();
				favorite.setUser_id(rs.getInt("user_id"));
				favorite.setMerchandise_id(rs.getInt("merchandise_id"));
				favorite.setFavorite_id(rs.getInt("favorite_id"));
				favorite_list.add(favorite);
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
		return favorite_list;
	}

	public ArrayList<Favorite> selectByFavorite(int merchandise_id) {

		/* 変数の宣言 */
		Connection con = null;
		Statement smt = null;
		ArrayList<Favorite> favoriteInfo = new ArrayList<Favorite>();

		try {
			// DBに接続
			con = getConnection();
			smt = con.createStatement();

			// SQL文
			String sql = "SELECT * FROM favorite_info WHERE merchandise_id ='" + merchandise_id + "'";

			// SQL文発行
			ResultSet rs = smt.executeQuery(sql);

			// 必要なユーザー情報の取得
			while (rs.next()) {
				Favorite favorite = new Favorite();
				favorite.setFavorite_id(Integer.parseInt(rs.getString("favorite_id")));
				favorite.setUser_id(Integer.parseInt(rs.getString("user_id")));
				favorite.setMerchandise_id(Integer.parseInt(rs.getString("Merchandise_id")));
				favoriteInfo.add(favorite);
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
		return favoriteInfo;
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
