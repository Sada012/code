/* 
 * プログラミング名：User 
 * プログラムの説明：ユーザーデータを取り扱うクラス 
 * 作成者： 
 * 作成日：2024年6月21日 
 */

package bean;

public class User {

	private int user_id; // ユーザーID 

	private String password; // パスワード 

	private String name; // 名前 

	private String nickname; // ニックネーム 

	private String address; // 住所 

	private String email; // メールアドレス 

	private int authority; //権限（1：管理者、2：一般ユーザー） 

	private String post_code; // 郵便番号 

	private String icon; // アイコン 
	
	private int listing_status; // 出品状況(1：未出品、2：出品済み)

	/* フィールド変数に初期値を設定 */
	public User() {

		this.user_id = 0;

		this.password = null;

		this.name = null;

		this.nickname = null;

		this.address = null;

		this.email = null;

		this.authority = 0;

		this.post_code = null;

		this.icon = null;
		
		this.listing_status = 0;

	}

	/* ユーザーIDのゲッターとセッター */
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	/* パスワードのゲッターとセッター */
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/* 名前のゲッターとセッター */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/* ニックネームのゲッターとセッター */
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/* 住所のゲッターとセッター */
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/* メールアドレスのゲッターとセッター */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/* 権限のゲッターとセッター */
	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	/* 郵便番号のゲッターとセッター */
	public String getPost_code() {
		return post_code;
	}

	public void setPost_code(String postcode) {
		this.post_code = postcode;
	}

	/* アイコンのゲッターとセッター */
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	/* 出品状況のゲッターとセッター */
	public int getListing_status() {
		return listing_status;
	}

	public void setListing_status(int listing_status) {
		this.listing_status = listing_status;
	}
}
