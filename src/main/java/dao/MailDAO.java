package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Mail;

public class MailDAO {

	private static String RDB_DRIVE = "org.mariadb.jdbc.Driver";
	private static String URL = "jdbc:mariadb://localhost/feemadb";
	private static String USER="root";
	private static String PASSWD="root123";
	
	public void insert(Mail mail) {

		Connection con = null;
		Statement smt = null;

		try {

			//getConnectionメソッドの呼び出し
			con = MailDAO.getConnection();
			//createStatementメソッドの呼び出し
			smt = con.createStatement();
			
			//SQL文 
			String sql = "INSERT INTO mail_info VALUES('" + mail.getMerchandise_id() + "','" + mail.getSender() + "','" + mail.getRecipient() 
			+ "','" + mail.getTemplate() + "','" + mail.getAlready_id() + "','" + mail.getAttached() + "','" + mail.getPurchase_id()
			+ "','" + mail.getSent_date() + "','" + mail.getReception_date() + "','" + mail.getSubject() + "','" + mail.getLetter_body() + "','"; 

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
}
