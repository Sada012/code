package servlet;

import java.io.IOException;

import bean.User;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/userInfoUpdate")
public class UserInfoUpdateServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//エラーメッセージ
		String error = "";
		String cmd = "";

		// セッションオブジェクトの生成
		HttpSession session = request.getSession();
		// ユーザー情報の取得
		User user = (User) session.getAttribute("user");
		try {


			//画面から入力情報を受け取る処理
			String name = request.getParameter("name");
			String nickname = request.getParameter("nickname");
			String email = request.getParameter("email");
			String address = request.getParameter("address");

			UserDAO userDao = new UserDAO();

			int user_id = user.getUser_id();

			//画面から更新情報を受け取る
			user.setUser_id(user_id);
			user.setName(name);
			user.setNickname(nickname);
			user.setEmail(email);
			user.setAddress(address);

			//ユーザー情報が重複していないか判断する
			User userinfo = userDao.selectByUserInfo(Integer.toString(user_id));

			if (userinfo == null) {

				error = "ユーザー情報が存在しないため、ユーザー情報更新画面は表示出来ませんでした。";

				//cmdに値を登録
				cmd = "adminMenu";

				return;
			}

			//名前の未入力チェック
			if (name.equals("")) {

				error = "名前が未入力のため、ユーザー情報更新処理が行えませんでした。";

				//cmdに値を登録
				cmd = "adminMenu";

				return;

			}

			//ニックネーム未入力チェック
			if (nickname.equals("")) {

				error = "ニックネームが未入力のため、ユーザー情報更新処理が行えませんでした。";

				//cmdに値を登録
				cmd = "adminMenu";

				return;
			}

			//メールアドレス未入力チェック
			if (email.equals("")) {

				error = "メールアドレスが未入力のため、ユーザー情報更新処理が行えませんでした。";

				//cmdに値を登録
				cmd = "adminMenu";

				return;
			}

			//更新したユーザー情報を保存
			userDao.update(user);

			// リクエストスコープに登録
			request.setAttribute("admininfo", user);

		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、ユーザー情報画面は表示できませんでした。";

			cmd = "logout";

		} finally {
			if (error.equals("")) {
				if (user.getAuthority() == 1) {
					//フォワードをする。
					request.getRequestDispatcher("/view/adminMenu.jsp").forward(request, response);
				} else if(user.getAuthority() == 2) {
					//フォワードをする。
					request.getRequestDispatcher("/view/mypage.jsp").forward(request, response);
				}

			} else {
				//リクエストスコープにエラーメッセージを登録するa
				request.setAttribute("error", error);

				//リクエストスコープにcmdを登録する
				request.setAttribute("cmd", cmd);

				//error.jspへフォワードする
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

		}

	}

}
