/*
 * プログラミング名：Merchandise
 * プログラムの説明：商品データを取り扱うクラス
 * 作成者：
 * 作成日：2024年6月21日
 */

package bean;

public class Merchandise {

	private int merchandise_id;	//商品ID
	
	private int user_id;			// ユーザーID
	
	private String product_name;	// 商品名
	
	private int price;				// 価格
	
	private String kinds;			// 種類
	
	private String remarks;		// 備考
	
	private int exhibition_area;	// 出品地域(1～47で都道府県を分ける)
	
	private int sales_status;		// 販売状況(1.販売中 2.sold out)
	
	private String photo;			// 写真
	
	private String listing_date;	// 出品日
	
	/* フィールド変数に初期値を設定 */
	public Merchandise() {
		
		this.merchandise_id = 0;
		
		this.user_id = 0;
		
		this.product_name = null;
		
		this.price = 0;
		
		this.kinds = null;
		
		this.remarks = null;
		
		this.exhibition_area = 0;
		
		this.sales_status = 0;
		
		this.photo = null;
		
		this.listing_date = null;
		
	}

	/* 商品IDのゲッターとセッター */
	public int getMerchandise_id() {
		return merchandise_id;
	}

	public void setMerchandise_id(int merchandise_id) {
		this.merchandise_id = merchandise_id;
	}

	/* ユーザーIDのゲッターとセッター */
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	/* 商品名のゲッターとセッター */
	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	/* 価格のゲッターとセッター */
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	/* 種類のゲッターとセッター */
	public String getKinds() {
		return kinds;
	}

	public void setKinds(String kinds) {
		this.kinds = kinds;
	}

	/* 備考のゲッターとセッター */
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/* 出品地域(のゲッターとセッター */
	public int getExhibition_area() {
		return exhibition_area;
	}

	public void setExhibition_area(int exhibition_area) {
		this.exhibition_area = exhibition_area;
	}

	/* 販売状況のゲッターとセッター */
	public int getSales_status() {
		return sales_status;
	}

	public void setSales_status(int sales_status) {
		this.sales_status = sales_status;
	}

	/* 写真のゲッターとセッター */
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/* 出品日のゲッターとセッター */
	public String getListing_date() {
		return listing_date;
	}

	public void setListing_date(String listing_date) {
		this.listing_date = listing_date;
	}
}