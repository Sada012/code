/*
 * プログラミング名：Mailbox
 * プログラムの説明：受信したメールデータを取り扱うクラス
 * 作成者：
 * 作成日：2024年6月20日
 */

package bean;

public class Mailbox {
	
	private int mailbox_id;		// メールボックスID

	private int user_id;			// ユーザーID
	
	private int mail_id;			// メールID
	
	/* フィールド変数に初期値を設定 */
	public Mailbox() {
		
		this.mailbox_id = 0;
		
		this.user_id = 0;
		
		this.mail_id = 0;
		
	}
	
	/* メールボックスIDのゲッターとセッター */
	public int getMailbox_id() {
		
		return mailbox_id;
		
	}
	
	public void setMailbox_id(int mailbox_id) {
		
		this.mailbox_id = mailbox_id;
		
	}
	
	/* ユーザーIDのゲッターとセッター */
	public int getUser_id() {
		
		return user_id;
		
	}
	
	public void setUser_id(int user_id) {
		
		this.user_id = user_id;
		
	}
	
	/* メールIDのゲッターとセッター */
	public int getMail_id() {
		
		return mail_id;
		
	}
	
	public void setMail_id(int mail_id) {
		
		this.mail_id = mail_id;
		
	}
}
