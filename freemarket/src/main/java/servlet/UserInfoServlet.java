package servlet;

import java.io.IOException;

import bean.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/userInfo")

public class UserInfoServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//エラーメッセージ 
		String error = "";
		String cmd = "";

		try {
			// セッションスコープの保存領域を確保
			HttpSession session = request.getSession();

			// ユーザー情報の取得
			User user = (User) session.getAttribute("user");

			//セッション切れか確認
			if (user == null) {
				//セッション切れならerror.jspへフォワード
				error = "セッション切れの為、ユーザー情報は確認出来ません。";
				cmd = "logout";
			}
			
			if (user.getUser_id() == 0) {
				//priceエラーメッセージを設定 
				error = "表示対象のユーザー情報が存在しない為、ユーザー情報は表示できませんでした。 ";
				//cmdに値を登録 
				cmd = "logout";
				return;
			}

			// リクエストスコープに登録する 
			request.setAttribute("userinfo", user);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、ユーザー情報画面は表示できませんでした。";
			cmd = "logout";
		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。 <br>" + e;
			cmd = "login";
		} finally {
			if (error.equals("")) {
				//UserInfoへフォワードをする。 
				request.getRequestDispatcher("/view/userInfo.jsp").forward(request, response);
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
