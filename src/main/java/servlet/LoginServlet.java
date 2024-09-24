package servlet;

import java.io.IOException;

import bean.User;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//エラー変数の初期化
		String error = "";
		String cmd = "";

		//UserDAOのオブジェクト生成
		UserDAO userDaoObj = new UserDAO();
		User user = new User();
		try {
			//userid, passwordword入力パラメータを取得する。
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			//user情報の取得
			user = userDaoObj.selectByUser(email, password);

			//入力チェック
			if (user.getEmail() != null) {
				//セッションオブジェクト生成
				HttpSession session = request.getSession();
				
				//セッションにuserをuserで登録
				session.setAttribute("user", user);
				
				//クッキーに登録(email)
				Cookie cookieUser = new Cookie("email", email);
				cookieUser.setMaxAge(60 * 60 * 24 * 3);
				response.addCookie(cookieUser);
				
				//クッキーに登録(password)
				Cookie cookiePassword = new Cookie("password", password);
				cookiePassword.setMaxAge(60 * 60 * 24 * 5);
				response.addCookie(cookiePassword);
				
				//クッキーに登録(user_id)
				Cookie cookieUser_id = new Cookie("user_id", Integer.toString(user.getUser_id()));
				cookieUser_id.setMaxAge(60 * 60 * 24 * 5);
				response.addCookie(cookieUser_id);
				
				//クッキーに登録(name)
				Cookie cookieName = new Cookie("name", user.getName());
				cookieName.setMaxAge(60 * 60 * 24 * 5);
				response.addCookie(cookieName);
				
				//クッキーに登録(nickname)
				Cookie cookieNickname = new Cookie("nickname", user.getNickname());
				cookieNickname.setMaxAge(60 * 60 * 24 * 5);
				response.addCookie(cookieNickname);
				
				//クッキーに登録(address)
				Cookie cookieAddress = new Cookie("address", user.getAddress());
				cookieAddress.setMaxAge(60 * 60 * 24 * 5);
				response.addCookie(cookieAddress);
				
			} else {
				error = "入力データが間違っています！";
				cmd = "login";
			}
			//接続エラー
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、ログインできませんでした。";
			cmd = "error";
			
			//Exceptionエラー
		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。<br>" + e;
			cmd = "error";
		} finally {
			//エラーに何もなければ商品一覧に遷移
			if (error == "") {
				if(user.getAuthority() == 2) {
					request.getRequestDispatcher("/view/mypage.jsp").forward(request, response);
				} else if(user.getAuthority() == 1) {
					request.getRequestDispatcher("/view/adminMenu.jsp").forward(request, response);
				}
			//エラーに何か入っていればエラーjspに遷移
			} else if(error != "" && cmd == "login") {
				request.setAttribute("cmd", cmd);
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/login.jsp").forward(request, response);
			} else if(error != "" && cmd == "error") {
				request.setAttribute("cmd", cmd);
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}
