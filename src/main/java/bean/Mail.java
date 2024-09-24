/*
 * プログラミング名：Mail
 * プログラムの説明：メールデータを取り扱うクラス
 * 作成者：
 * 作成日：2024年6月20日
 */

package bean;

public class Mail {
	
	private int mail_id;				// メールID
	
	private int merchandise_id;		// 商品ID
	
	private int sender;				// 送信者
	
	private int recipient;				// 受信者
	
	private String template;			// テンプレート
	
	private int already_id;			// 既読確認
	
	private String attached;			// 添付ファイル
	
	private int purchase_id;			// 購入ID
	
	private String sent_date;			// 送信日時
	
	private String reception_date;		// 受信日時
	
	private String subject;			// 件名
	
	private String letter_body;		// 本文
	
	/* フィールド変数に初期値を設定 */
	public Mail() {
		
		this.mail_id = 0;
		
		this.merchandise_id = 0;
		
		this.sender = 0;
		
		this.recipient = 0;
		
		this.template = null;
		
		this.already_id = 0;
		
		this.attached = null;
		
		this.purchase_id = 0;
		
		this.sent_date = null;
		
		this.reception_date = null;
		
		this.subject = null;
		
		this.letter_body = null;
		
	}
	
	/* メールIDのゲッターとセッター */
	public int getMail_id() {
		
		return mail_id;
		
	}
	
	public void setMail_id(int mail_id) {
		
		this.mail_id = mail_id;
		
	}
	
	/* 商品IDのゲッターとセッター */
	public int getMerchandise_id() {
		
		return merchandise_id;
		
	}
	
	public void setMerchandise_id(int merchandise_id) {
		
		this.merchandise_id = merchandise_id;
		
	}
	
	/* 送信者のゲッターとセッター */
	public int getSender() {
		
		return sender;
		
	}
	
	public void setSender(int sender) {
		
		this.sender = sender;
		
	}
	
	/* 受信者のゲットとセット */
	public int getRecipient() {
		
		return recipient;
		
	}
	
	public void setRecipient(int recipient) {
		
		this.recipient = recipient;
		
	}
	
	/* テンプレートのゲットとセット */
	public String getTemplate() {
		
		return template;
		
	}
	
	public void setTemplate(String template) {
		
		this.template = template;
		
	}
	
	/* 既読確認のゲッターとセッター */
	public int getAlready_id() {
		
		return already_id;
		
	}
	
	public void setAlready_id(int already_id) {
		
		this.already_id = already_id;
		
	}
	
	/* 添付ファイルのゲッターとセッター */
	public String getAttached() {
		
		return attached;
		
	}
	
	public void setAttached(String attached) {
		
		this.attached = attached;
		
	}
	
	/* 購入IDのゲッターとセッター */
	public int getPurchase_id() {
		
		return purchase_id;
		
	}
	
	public void setPurchase_id(int purchase_id) {
		
		this.purchase_id = purchase_id;
		
	}
	
	/* 送信日時のゲッターとセッター */
	public String getSent_date() {
		
		return sent_date;
		
	}
	
	public void setSent_date(String sent_date) {
		
		this.sent_date = sent_date;
		
	}
	
	/* 受信日時のゲッターとセッター */
	public String getReception_date() {
		
		return reception_date;
		
	}
	
	public void setReception_date(String reception_date) {
		
		this.reception_date = reception_date;
		
	}
	
	/* 件名のゲッターとセッター */
	public String getSubject() {
		
		return subject;
		
	}
	
	public void setSubject(String subject) {
		
		this.subject= subject;
		
	}
	
	/* 本文のゲッターとセッター */
	public String getLetter_body() {
		
		return letter_body;
		
	}
	
	public void setLetter_body(String letter_body) {
		
		this.letter_body = letter_body;
		
	}
}
