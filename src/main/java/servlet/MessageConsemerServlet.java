package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/messageConsemer")

public class MessageConsemerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 変数の初期化
		String error = "";

		String cmd = "";
		
		String message = "";
		
		// セッションオブジェクトの生成
		HttpSession session = request.getSession();
		
		try {
			
			// セッションからuserの情報を取得
			String email = (String)session.getAttribute("email");
						
			// セッションが切れていた場合
			if(email == null) {
							
				error = "セッションが切れているため、メッセージを表示できません！";
							
				cmd = "login";
							
			}
			
			// パラメータ(メッセージ)の取得
			message = request.getParameter("mesage");
			
		}catch (IllegalStateException e) {

			error = "DB接続エラーの為、メッセージの表示は行えません。";
			
			cmd = "login";

		} catch (Exception e) {

			error = "予期せぬエラーが発生しました。 <br>" + e;
			
			cmd = "login";

		} finally {
			
			if(error != "") {
				
				// リクエストスコープに登録
				request.setAttribute("error", error);
				
				request.setAttribute("cmd", cmd);
				
				// error画面に遷移
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
				
			} else {
				
				
				
			}

		}

	}

}
