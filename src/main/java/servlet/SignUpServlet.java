package servlet; 

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bean.User;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/signUp") 
@MultipartConfig
public class SignUpServlet extends HttpServlet { 
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 

		// 変数の初期化 
		String error = ""; 
		String cmd = ""; 
		int cmd2 = 0;

		try { 
			//ファイル取得用のimage情報を受け取る
			Part filePart = request.getPart("icon");

			//ルート情報とファイルパスを管理する変数を初期化
			String uploadDir = "";
			String filePath = "";
			String icon = "";
			

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
				
				icon = "/img/" + fileName;
				
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

			}
			
			// パラメータの取得
			String password = request.getParameter("password");
			String password_sub = request.getParameter("password_sub");
			String name = request.getParameter("name");
			String nickname = request.getParameter("nickname"); 
			String address = request.getParameter("address"); 
			String email = request.getParameter("email");
			int authority = Integer.parseInt(request.getParameter("authority"));
			String post_code = request.getParameter("post_code");
			cmd2 = authority;

			// 入力値チェック
			if(password == null) { 
				error = "パスワードが未入力です！"; 
				cmd = "signUp"; 
			} else if(password_sub == null) { 
				error = "パスワード(確認用)が未入力です！"; 
				cmd = "signUp"; 
			} else if(!password.equals(password_sub)) { 
				error = "パスワードが一致しません！"; 
				cmd = "signUp"; 
			} else if(name == null) { 
				error = "名前が未入力です！"; 
				cmd = "signUp"; 
			} else if(nickname == null) { 
				error = "ニックネームが未入力です！"; 
				cmd = "signUp"; 
			} else if(address == null) { 
				error = "住所が未入力です！"; 
				cmd = "signUp"; 
			} else if(email == null) { 
				error = "メールアドレスが未入力です！"; 
				cmd = "signUp"; 
			} else if(post_code == null) { 
				error = "郵便番号が未入力です！"; 
				cmd = "signUp"; 
			} else if(icon == null || icon.equals("")) {
				icon = "/img/common/icon_user.png";
			} 


			// Userクラスのインスタンス化 
			User user = new User(); 

			// UserDAOクラスのインスタンス化 
			UserDAO userDaoObj = new UserDAO();

			// メールアドレスの重複チェック用メソッドの呼び出し 
			String email_alr = userDaoObj.selectByEmail(email);

			// メールアドレスの重複チェック 
			if(email_alr != null) { 
				error = "入力されたメールアドレスはすでに登録されています！"; 
				cmd = "signUp"; 
			} else { 
				// ユーザーオブジェクトに受け取った値を代入 
				user.setPassword(password);
				user.setName(name); 
				user.setNickname(nickname); 
				user.setAddress(address); 
				user.setEmail(email);
				user.setAuthority(authority);
				user.setPost_code(post_code);
				user.setIcon(icon);
				user.setListing_status(1);

				// 新規登録用メソッドの呼び出し 
				userDaoObj.insert(user); 
			} 
		}catch (IllegalStateException e) { 
			error = "DB接続エラーの為、新規登録は行えません。"; 
			cmd = "login"; 
		} catch (Exception e) { 
			error = "予期せぬエラーが発生しました。 <br>" + e; 
			cmd = "login"; 
		} finally { 
			// リクエストスコープに登録 
			request.setAttribute("error", error); 
			request.setAttribute("cmd", cmd); 

			if(error != "") {
				// error画面に遷移 
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			} else if(cmd2 == 2){ 
				// 一覧画面に遷移 
				request.getRequestDispatcher("/view/login.jsp").forward(request, response);
			} else if(cmd2 == 1) {
				request.getRequestDispatcher("/view/adminMenu.jsp").forward(request, response);
			}
		} 
	} 
}
