package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Mailbox;

public class MailboxDAO {

	private static String RDB_DRIVE = "org.mariadb.jdbc.Driver";
	private static String URL = "jdbc:mariadb://localhost/feemadb";
	private static String USER="root";
	private static String PASSWD="root123";
	
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
	
	
	public void insert(Mailbox mailbox) {

		Connection con = null;
		Statement smt = null;

		try {

			//getConnectionメソッドの呼び出し
			con = MailboxDAO.getConnection();
			//createStatementメソッドの呼び出し
			smt = con.createStatement();
			
			//SQL文 
			String sql = "INSERT INTO mail_info VALUES('"+ mailbox.getUser_id() + "','" + mailbox.getMail_id() 
			+ "','";
			// SQL文発行 
			smt.executeUpdate(sql); 

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			// リソースの解放
			if ( smt != null ) {
				try { smt.close(); } catch (SQLException ignore) { }
			}
			if ( con != null ) {
				try { con.close(); } catch (SQLException ignore) { }
			}
		}
	}
	
	
}
