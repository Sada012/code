/*
 * プログラミング名：Purchase
 * プログラムの説明：購入データを取り扱うクラス
 * 作成者：
 * 作成日：2024年6月20日
 */

package bean;

public class Purchase {

	private int purchase_id;		//購入ID
	
	private int user_id;			//ユーザーID
	
	private String purchase_day;	//購入日
	
	private int trading_status;		//発送状況(1.発送済み 2.未発送)
	
	private int deposit_status;		//入金状況(1.支払い済 2.未払い）
	
	private int payment_method;		//支払方法(1.カード2.電子マネー３.コンビニ）
	
	private int merchandise_id;		//商品ID

	/* フィールド変数に初期値を設定 */
	public Purchase() {
		
		this.purchase_id = 0;
		this.user_id = 0;
		this.purchase_day = null;
		this.trading_status = 0;
		this.deposit_status = 0;
		this.payment_method = 0;
		this.merchandise_id = 0;
		
	}

	/* 購入IDのゲッターとセッター */
	public int getPurchase_id() {
		return purchase_id;
	}

	public void setPurchase_id(int purchase_id) {
		this.purchase_id = purchase_id;
	}

	/* ユーザーIDのゲッターとセッター */
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	/* 購入日のゲッターとセッター */
	public String getPurchase_day() {
		return purchase_day;
	}

	public void setPurchase_day(String purchase_day) {
		this.purchase_day = purchase_day;
	}

	/* 取引状況のゲッターとセッター */
	public int getTrading_status() {
		return trading_status;
	}

	public void setTrading_status(int trading_status) {
		this.trading_status = trading_status;
	}

	/* 入金状況のゲッターとセッター */
	public int getDeposit_status() {
		return deposit_status;
	}

	public void setDeposit_status(int deposit_status) {
		this.deposit_status = deposit_status;
	}

	/* 支払い方法のゲッターとセッター */
	public int getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(int payment_method) {
		this.payment_method = payment_method;
	}

	/* 商品IDのゲッターとセッター */
	public int getMerchandise_id() {
		return merchandise_id;
	}

	public void setMerchandise_id(int merchandise_id) {
		this.merchandise_id = merchandise_id;
	}
}