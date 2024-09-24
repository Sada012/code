package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.User;

public class UserDAO {

	private static String RDB_DRIVE = "org.mariadb.jdbc.Driver";
	private static String URL = "jdbc:mariadb://localhost/feemadb";
	private static String USER="root";
	private static String PASSWD="root123";

	/* 全てのユーザーの一覧 */
	public ArrayList<User> selectAll() {

		Connection con = null;
		Statement smt = null;

		//AllayListオブジェクトを生成
		ArrayList<User> userList = new ArrayList<User>();

		//SQL文を文字列として定義
		String sql = "SELECT * FROM user_info ORDER BY user_id";

		try {

			//Connectionオブジェクトを生成
			con = getConnection();

			//Statementオブジェクトを生成
			smt = con.createStatement();

			//SQLをDBへ発行
			ResultSet rs = smt.executeQuery(sql);

			//検索件数分すべて取り出して格納
			while (rs.next()) {
				User user = new User();
				user.setUser_id(rs.getInt("user_id"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setNickname(rs.getString("nickname"));
				user.setAddress(rs.getString("address"));
				user.setEmail(rs.getString("email"));
				user.setAuthority(rs.getInt("authority"));
				user.setPost_code(rs.getString("post_code"));
				user.setIcon(rs.getString("icon"));
				user.setListing_status(rs.getInt("listing_status"));

				userList.add(user);
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
		return userList;
	}

	/* User情報確認 */
	public User selectByUserInfo(String user_id) {

		/* 変数の宣言 */
		Connection con = null;
		Statement smt = null;
		User user = new User();

		try {
			// DBに接続
			con = getConnection();
			smt = con.createStatement();

			// SQL文
			String sql = "SELECT * FROM user_info WHERE user_id ='" + user_id + "'";

			// SQL文発行
			ResultSet rs = smt.executeQuery(sql);

			// 必要なユーザー情報の取得
			while (rs.next()) {
				user.setUser_id(rs.getInt("user_id"));
				user.setName(rs.getString("name"));
				user.setNickname(rs.getString("nickname"));
				user.setAddress(rs.getString("address"));
				user.setEmail(rs.getString("email"));
				user.setPost_code(rs.getString("post_code"));
				user.setAuthority(rs.getInt("authority"));
				user.setPassword(rs.getString("password"));
				user.setIcon(rs.getString("icon"));
				user.setListing_status(rs.getInt("listing_status"));
			}
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
		return user;
	}

	/* ユーザー情報の更新 */
	public void update(User user) {

		/* 変数の宣言 */
		Connection con = null;
		Statement smt = null;

		try {

			//DBに接続
			con = UserDAO.getConnection();
			smt = con.createStatement();

			//SQL文
			String sql = "UPDATE user_info SET name='" + user.getName() + "',nickname='" + user.getNickname() +
					"',address='" + user.getAddress() + "',email='" + user.getEmail() + "' WHERE user_id='"
					+ user.getUser_id() + "'";

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
	
	/* ユーザー情報の更新 */
	public void updateListing_status(User user) {

		/* 変数の宣言 */
		Connection con = null;
		Statement smt = null;

		try {

			//DBに接続
			con = UserDAO.getConnection();
			smt = con.createStatement();

			//SQL文
			String sql = "UPDATE user_info SET listing_status=2 WHERE user_id='" + user.getUser_id() + "'";

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

	/* ログイン用のメソッド */
	public User selectByUser(String email,String password){ 

		/* 変数の宣言 */
		Connection con = null; 
		Statement smt = null; 
		User user = new User(); 

		try{ 
			// DBに接続
			con = getConnection(); 
			smt = con.createStatement(); 

			//SQL文 
			String sql =  "SELECT * FROM user_info WHERE Email ='"+email+"' AND password='"+password+"'"; 

			//SQL文の結果をセット 
			ResultSet rs = smt.executeQuery(sql);    

			//検索件数分すべて取り出して格納
			while (rs.next()) {
				user.setUser_id(rs.getInt("user_id"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setNickname(rs.getString("nickname"));
				user.setAddress(rs.getString("address"));
				user.setEmail(rs.getString("email"));
				user.setAuthority(rs.getInt("authority"));
				user.setPost_code(rs.getString("post_code"));
				user.setIcon(rs.getString("icon"));
				user.setListing_status(rs.getInt("listing_status"));
			}
		}catch(Exception e){ 
			throw new IllegalStateException(e); 
		}finally{ 
			if( smt != null ){ 
				try{smt.close();}catch(SQLException ignore){} 
			} 
			if( con != null ){ 
				try{con.close();}catch(SQLException ignore){} 
			} 
		} 
		return user; 
	} 

	/* 新規登録用メソッド */
	public void insert(User user) { 

		/* 変数の宣言 */
		Connection con = null;
		Statement smt = null;

		try{ 
			// DBに接続 
			con = getConnection(); 
			smt = con.createStatement(); 

			//SQL文 
			String sql = "INSERT INTO user_info VALUES('','" 
					+ user.getPassword() + "','" 
					+ user.getName() + "','" 
					+ user.getNickname() + "','"
					+ user.getAddress() + "','"
					+ user.getEmail() + "','"
					+ user.getAuthority() + "','"
					+ user.getPost_code() + "','"
					+ user.getIcon() + "','"
					+ user.getListing_status()
					+ "')"; 

			// SQL文発行 
			smt.executeUpdate(sql); 

		}catch(Exception e){ 
			throw new IllegalStateException(e); 
		}finally{ 

			if( smt != null ){ 
				try{smt.close();}catch(SQLException ignore){} 
			} 
			if( con != null ){ 
				try{con.close();}catch(SQLException ignore){} 
			} 
		} 
	} 

	/* emailの重複を防ぐメソッド */
	public String selectByEmail(String email) { 

		/* 変数の宣言 */ 
		Connection con = null; 
		Statement smt = null; 
		String email2 = null; 

		try{ 
			// DBに接続 
			con = getConnection(); 
			smt = con.createStatement(); 
			
			//SQL文 
			String sql =  "SELECT * FROM user_info WHERE email= '" + email + "'"; 

			//SQL文の結果をセット 
			ResultSet rs = smt.executeQuery(sql); 
			
			if (rs != null) {
				//検索件数分すべて取り出して格納
				while (rs.next()) {
					//emailが存在していたかの結果を代入する
					email2 = rs.getString("email"); 
				}
			}
		}catch(Exception e){ 
			throw new IllegalStateException(e); 
		}finally{ 
			if( smt != null ){ 
				try{smt.close();}catch(SQLException ignore){} 
			} 
			if( con != null ){ 
				try{con.close();}catch(SQLException ignore){} 
			} 
		} 
		return email2; 
	}
	
	public ArrayList<User> selectAll(int listing_status) {

		Connection con = null;
		Statement smt = null;

		//AllayListオブジェクトを生成
		ArrayList<User> userList = new ArrayList<User>();

		//SQL文を文字列として定義
		String sql = "SELECT * FROM user_info Where listing_status='" + listing_status + "'";

		try {

			//Connectionオブジェクトを生成
			con = getConnection();

			//Statementオブジェクトを生成
			smt = con.createStatement();

			//SQLをDBへ発行
			ResultSet rs = smt.executeQuery(sql);

			//検索件数分すべて取り出して格納
			while (rs.next()) {
				User user = new User();
				user.setUser_id(rs.getInt("user_id"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setNickname(rs.getString("nickname"));
				user.setAddress(rs.getString("address"));
				user.setEmail(rs.getString("email"));
				user.setAuthority(rs.getInt("authority"));
				user.setPost_code(rs.getString("post_code"));
				user.setIcon(rs.getString("icon"));
				user.setListing_status(rs.getInt("listing_status"));

				userList.add(user);
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
		return userList;
	}

	//データベース接続情報を利用してデータベースに接続するクラスメソッドgetConnection
	private static Connection getConnection() {
		try {
			Class.forName(RDB_DRIVE);
			Connection con = DriverManager.getConnection(URL,USER,PASSWD);
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}





	/**
	 * 管理者ユーザー詳細メソッド
	 * @param 特定のユーザーのuser_id
	 * @return ユーザーIDの情報
	 */
	public User selectByUser_id(int user_id){

		Connection con = null;
		Statement smt = null;

		//①Bookオブジェクトを生成
		User detail_user = new User();

		try{

			//②検索用SQL文を文字列として定義
			String sql = "SELECT user_id,name,nickname,email,authority,icon FROM user_info WHERE user_id = '" + user_id + "'";

			//③Connectionオブジェクトを生成
			con = getConnection();

			//④Statementオブジェクトを生成
			smt = con.createStatement();

			//⑤検索結果をセット
			ResultSet rs = smt.executeQuery(sql);

			//⑥書籍データのみ取り出し格納
			if(rs.next()){
				detail_user.setUser_id(rs.getInt("user_id"));
				detail_user.setName(rs.getString("name"));
				detail_user.setNickname(rs.getString("nickname"));
				detail_user.setEmail(rs.getString("email"));
				detail_user.setAuthority(rs.getInt("authority"));
				detail_user.setIcon(rs.getString("icon"));


			}
		}catch(Exception e){
			throw new IllegalStateException(e);
		}finally{
			//⑦⑧StatementおよびConnectionオブジェクトをクローズ
			if( smt != null ){
				try{smt.close();}catch(SQLException ignore){}
			}
			if( con != null ){
				try{con.close();}catch(SQLException ignore){}
			}
		}
		return detail_user;
	}

}
