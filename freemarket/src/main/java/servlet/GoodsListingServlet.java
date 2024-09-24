package servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bean.Merchandise;
import bean.User;
import dao.MerchandiseDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

//アノテーション
@WebServlet("/goodsListing")
@MultipartConfig
public class GoodsListingServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//初期化
		String error = null;
		String cmd = null;

		//DAOクラスのオブジェクトを生成
		MerchandiseDAO objMerchandiseDao = new MerchandiseDAO();

		try {
			// セッションスコープの保存領域を確保
			HttpSession session = request.getSession();

			// ユーザー情報の取得
			User user = (User) session.getAttribute("user");

			//セッション切れか確認
			if (user == null) {
				//セッション切れならerror.jspへフォワード
				error = "セッション切れの為、出品は出来ません。";
				cmd = "logout";
			}
			
			//ファイル取得用のimage情報を受け取る
			Part filePart = request.getPart("photo");

			//ルート情報とファイルパスを管理する変数を初期化
			String uploadDir = "";
			String filePath = "";
			String photo = "";
			

			//ファイルサイズを元にファイルの有無を確認
			if (filePart.getSize() != 0) {

				//アップロードされたファイルの詳細情報を取得
				String contentDisposition = filePart.getHeader("content-disposition");

				//ファイル名を管理する変数
				String fileName = "";
				
				//ファイル名を取得するための正規表現パターンを設定
				Pattern pattern = Pattern.compile("filename=\"(.*)\"");
				
				//正規表現パターンを使用して、詳細情報からファイル名を抽出
				Matcher matcher = pattern.matcher(contentDisposition);
				
				//抽出したファイル名が存在していればファイル名を管理する変数に代入、なければ空白を代入
				if (matcher.find()) {
					fileName = matcher.group(1);
				} else {
					fileName = "";
				}

				// 保存先ディレクトリを設定
				uploadDir = "C:\\usr\\kis_java_pkg_2023\\workspace\\freemarket\\src\\main\\webapp\\img";
				
				//アップロード先のディレクトリが存在しない場合に、そのディレクトリを作成
				File uploadDirectory = new File(uploadDir);
				if (!uploadDirectory.exists()) {
					uploadDirectory.mkdirs();
				}
				
				photo = "/img/" + fileName;
				
				while(objMerchandiseDao.selectByPhoto(photo) != 0){
					String fileName_top = fileName.substring(0,fileName.indexOf("."));
					String fileName_under = fileName.substring(fileName.indexOf("."));
					fileName = fileName_top + "(1)" + fileName_under;
					photo = "/img/" + fileName;
				}
				
				//アップロードした画像ファイルパス
				filePath = uploadDir + "/" + fileName;
				
				// デバッグ用にパスを出力
				System.out.println("保存されたパス: " + filePath);
				
				//ファイルの保存処理（アップロードされたファイルをサーバー上の指定されたディレクトリに保存）
				try (InputStream inputStream = filePart.getInputStream()) {
					Files.copy(inputStream, new File(filePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
				}

				// 保存後にファイルの存在をチェック
				File savedFile = new File(filePath);
				if (!savedFile.exists()) {
					throw new IOException("保存に失敗しました。");
				}
				
				//リクエストスコープにファイル名を設定
				request.setAttribute("fileName", fileName);

			} else {
				error = "ファイルがありません";
			}
				int user_id = user.getUser_id();
				String product_name = request.getParameter("product_name");
				String price = request.getParameter("price");
				String kinds = request.getParameter("kinds");
				String remarks = request.getParameter("remarks");

			if (product_name == null) {
				//未入力エラー
				error = "商品名が未入力の為、商品出品処理は行えませんでした。";
				cmd = "list";
			} else if (price == null) {
				error = "価格が未入力の為、商品出品処理は行えませんでした。";
				cmd = "list";
			} else if (kinds == null) {
				error = "種類が未入力の為、商品出品処理は行えませんでした。";
				cmd = "list";
			} else if (remarks == null) {
				error = "備考が未入力の為、商品出品録処理は行えませんでした。";
				cmd = "list";
			} else {
				//登録する商品情報を格納するBookオブジェクトを生成
				Merchandise merchandise = new Merchandise();

				merchandise.setUser_id(user_id);
				merchandise.setProduct_name(product_name);
				//画面からの価格の入力情報を受け取り、merchandiseオブジェクトに格納
				merchandise.setPrice(Integer.parseInt(price));
				//画面からの種類の入力情報を受け取り、merchandiseオブジェクトに格納
				merchandise.setKinds(kinds);
				//画面からの備考の入力情報を受け取り、merchandiseオブジェクトに格納
				merchandise.setRemarks(remarks);
				//画面からの写真の入力情報を受け取り、merchandiseオブジェクトに格納
				merchandise.setPhoto(photo);

				objMerchandiseDao.insert(merchandise);
				
				UserDAO userObjDao = new UserDAO();
				
				userObjDao.updateListing_status(user);
			}			

		//データベース接続エラー
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			cmd = "logout";

			//価格の入力が数字以外の場合
		} catch (NumberFormatException e) {
			error = "価格の値が不正の為、商品出品処理は行えませんでした。";
			cmd = "list";
		} catch (Exception e) {
			error = "例外が起きました" + e;

		} finally {
			//エラーがない場合一覧表示
			if (error == null) {
				//⑥「ListServlet」へフォワード処理
				request.getRequestDispatcher("/view/mypage.jsp").forward(request, response);
			} else {

				request.setAttribute("error", error); //リクエストスコープエラーをセットする
				request.setAttribute("cmd", cmd); //リクエストスコープcmdをセットする

				//エラー画面へフォワード
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			}

		}
	}
}