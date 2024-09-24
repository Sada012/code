/*
 * プログラミング名：Favotite
 * プログラムの説明：お気に入りデータを取り扱うクラス
 * 作成者：
 * 作成日：2024年6月20日
 */

package bean;

public class Favorite {
	
	private int favorite_id;       // お気に入りID
	
	private int merchandise_id;    // 商品ID
	
	private int user_id;           // ユーザーID
	
	/* フィールド変数に初期値を設定 */
	public Favorite() {
		
		this.favorite_id = 0;
		
		this.merchandise_id = 0;
		
		this.user_id = 0;
		
	}
	
	/* お気に入りIDのゲッターとセッター */
	public int getFavorite_id() {
		
		return favorite_id;
		
	}
	
	public void setFavorite_id(int favorite_id) {
		
		this.favorite_id = favorite_id;
		
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
}
