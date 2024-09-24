package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.Favorite;
import bean.Merchandise;
import bean.User;
import dao.FavoriteDAO;
import dao.MerchandiseDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/favoriteList")

public class FavoriteListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 変数の初期化
		String error = "";

		String cmd = "";
		
		// 配列宣言
		ArrayList<Favorite> list = new ArrayList<Favorite>();
		
		// 配列宣言
		ArrayList<Merchandise> merchandiseList = new ArrayList<Merchandise>();
		
		// セッションオブジェクトの生成
		HttpSession session = request.getSession();
		
		try {
			
			// ユーザー情報の取得
			User user = (User) session.getAttribute("user");
																	
			// セッションが切れていた場合
			if(user == null) {					
				error = "セッションが切れているため、ユーザー情報が表示できません！";					
				cmd = "login";
													
			}
			
			// FavoriteDAOクラスのインスタンス化
			FavoriteDAO favoriteObjDao = new FavoriteDAO();
			MerchandiseDAO merchandiseObjDao = new MerchandiseDAO();
			
			// お気に入り商品を確認するためのメソッドの呼び出し
			list = favoriteObjDao.selectByuser_id(user.getUser_id());
			
			
			for(int i = 0 ; i < list.size() ; i++) {
				int merchandise = list.get(i).getMerchandise_id();
				Merchandise merchandiseInfo = merchandiseObjDao.selectByMerchandise_id("" + merchandise);
				merchandiseList.add(merchandiseInfo);
				
			}
			
		}catch (IllegalStateException e) {

			error = "DB接続エラーの為、お気に入り一覧のは行えません。";
			
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
				
				// リクエストスコープに登録
				request.setAttribute("list", list);
				
				// 一覧画面に遷移
				request.getRequestDispatcher("/view/favoriteList.jsp").forward(request, response);
				
			}

		}

	}

}
