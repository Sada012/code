package servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bean.Merchandise;
import bean.Purchase;
import bean.User;
import dao.MerchandiseDAO;
import dao.PurchaseDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import util.SendMail;

@WebServlet("/stockUpdate")
@MultipartConfig
public class StockUpdateServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//エラーメッセージとcmdを格納
		String error = "";
		String cmd = "";

		// セッションオブジェクトの生成
		HttpSession session = request.getSession();

		try {

			// ユーザー情報の取得
			User user = (User) session.getAttribute("user");

			//セッション切れか確認
			if (user == null) {
				//セッション切れならerror.jspへフォワード
				error = "セッション切れの為、詳細の確認が出来ません。";
				cmd = "logout";

				return;
			}

			//ファイル取得用のimage情報を受け取る
			Part filePart = request.getPart("photo");

			//ルート情報とファイルパスを管理する変数を初期化
			String uploadDir = "";
			String filePath = "";
			String photo = "";

			// 宣言
			MerchandiseDAO merchandiseDao = new MerchandiseDAO();
			Merchandise merchandise = new Merchandise();


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
				
				while (merchandiseDao.selectByPhoto(photo) != 0){
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
				merchandise.setPhoto(photo);

			}

			//画面から入力情報を受け取る処理
			String merchandise_id = request.getParameter("merchandise_id");
			String product_name = request.getParameter("product_name");
			String price = request.getParameter("price");
			String kinds = request.getParameter("kinds");
			String remarks = request.getParameter("remarks");
			String trading_status = request.getParameter("trading_status");

			//画面から更新情報を受け取る
			merchandise.setMerchandise_id(Integer.parseInt(merchandise_id));
			merchandise.setProduct_name(product_name);
			merchandise.setPrice(Integer.parseInt(price));
			merchandise.setKinds(kinds);
			merchandise.setRemarks(remarks);


			if (merchandise_id == null) {

				error = "商品が存在しないため、出品物更新画面は表示出来ませんでした。";

				//cmdに値を登録
				cmd = "mypage";

				return;
			}

			//名前の未入力チェック
			if (product_name.equals("")) {

				error = "名前が未入力のため、ユーザー情報更新処理が行えませんでした。";

				//cmdに値を登録
				cmd = "mypage";

				return;

			}

			//ニックネーム未入力チェック
			if (price.equals("")) {

				error = "ニックネームが未入力のため、ユーザー情報更新処理が行えませんでした。";

				//cmdに値を登録
				cmd = "mypage";

				return;
			}

			//メールアドレス未入力チェック
			if (kinds.equals("")) {

				error = "メールアドレスが未入力のため、ユーザー情報更新処理が行えませんでした。";

				//cmdに値を登録
				cmd = "mypage";

				return;
			}

			//住所未入力チェック
			if (remarks.equals("")) {

				error = "住所が未入力のため、ユーザー情報更新処理が行えませんでした。";

				//cmdに値を登録
				cmd = "mypage";

				return;
			}

			// 出品商品を確認するためのメソッドの呼び出し
			merchandiseDao.update(merchandise);

			PurchaseDAO purchaseDao = new PurchaseDAO();

			Purchase purchase = purchaseDao.selectByPurchase("" + merchandise.getMerchandise_id());

			if (purchase.getTrading_status() == 2) {
				
			purchase.setTrading_status(Integer.parseInt(trading_status));

			purchaseDao.situationUpdate(purchase);

			if (Integer.parseInt(trading_status) == 1) {

				User sender = new User();
				UserDAO userDao = new UserDAO();

				sender = userDao.selectByUser_id(merchandise.getMerchandise_id());

				SendMail mail = new SendMail();
				sender = userDao.selectByUser_id(purchase.getUser_id());
				mail.post(sender, merchandise, 1);

			}
			}

			request.setAttribute("merchandise", merchandise);

			//DBエラー処理
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、出品物リストは表示できません。";

			cmd = "logout";

		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。<br>" + e;

		} finally {
			if (error.equals("")) {
				//stockList.jspへフォワードする
				request.getRequestDispatcher("/view/mypage.jsp").forward(request, response);

			} else {
				//リクエストスコープにエラーメッセージを登録する
				request.setAttribute("error", error);

				//リクエストスコープにcmdを登録する
				request.setAttribute("errorCmd", cmd);

				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}

	}

}
