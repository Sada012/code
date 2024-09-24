package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.User;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/userList")

public class UserListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = null;

		try {
			//userDAOクラスをインスタンス化
			UserDAO userDaoObj = new UserDAO();
			
			//ArrayListオブジェクトを生成
			ArrayList<User> user = new ArrayList<User>();
			
			//userDAOクラスのsellerSelectAllメソッドを利用
			user = userDaoObj.selectAll();
			
			//user_listでリクエストスコープに登録
			request.setAttribute("user_list", user);
			

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			request.setAttribute("error", error);
			request.setAttribute("cmd", "logout");

		}finally {
			if(error == null) {
				//sellerList.jspへフォワードする
				request.getRequestDispatcher("/view/userList.jsp").forward(request, response);
				
				//error.jspへフォワードする
			}else {
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
			
		}

	}

}