package servlet;

import java.io.IOException;

import bean.Favorite;
import bean.User;
import dao.FavoriteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/favorite")
public class FavoriteServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = "";
		String cmd = "";
		User user = null;

		try {
			HttpSession session = request.getSession();

			// ログインしていなかったらエラー
			user = (User) session.getAttribute("user");
			if (user == null) {
				error = "セッション切れの為、カートに追加できません。";
				cmd = "logout";
				return;
			}

			//取得されたIDの取得
			String merchandise_id = request.getParameter("merchandise_id");

			//favoriteDaoオブジェクトの生成
			FavoriteDAO favoriteDao = new FavoriteDAO();
			
			//favoriteオブジェクトの生成
			Favorite favorite = new Favorite();

			//セレクト文でfavorite_id引数にfavoriteDAOに格納
			//favoriteDao.selectByuser_id(user.getUser_id());
			
			favorite.setMerchandise_id(Integer.parseInt(merchandise_id));
			favorite.setUser_id(user.getUser_id());
			

			favoriteDao.insert(favorite);
			
			
			
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、カートに追加はできません。";
			cmd = "goodsList";
		} finally {
			if (error.equals("")) {
				request.getRequestDispatcher("/view/insertIntoCart.jsp").forward(request, response);
			} else {
				request.setAttribute("cmd", cmd);
				request.setAttribute("error", error);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}

	}
}
