package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.Merchandise;
import bean.User;
import dao.MerchandiseDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/sellerList")

public class SellerListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = null;
		String cmd = null;

		try {
			//userDAOクラスをインスタンス化
			UserDAO userDaoObj = new UserDAO();

			//ArrayListオブジェクトを生成
			ArrayList<User> seller = new ArrayList<User>();

			//userDAOクラスのselectAllメソッドを利用
			seller = userDaoObj.selectAll(2);

			//MerchandiseDAOクラスのインスタンス化
			var merchandiseDaoObj = new MerchandiseDAO();
			
			//合計額の初期化
			ArrayList<Integer> totalList = new ArrayList<Integer>();

			for (int i = 0; i < seller.size(); i++) {
					//出品者の商品をすべて格納
					ArrayList<Merchandise> merchandise = merchandiseDaoObj.userSelectByMerchandise(Integer.toString(seller.get(i).getUser_id()));

					int total = 0;

					for (int j = 0; j < merchandise.size(); j++) {
						//取引状況が入金済みになった場合
						if (merchandise.get(j).getSales_status() == 2) {
							//合計値を更新
							total += merchandise.get(j).getPrice();
						}
					}
					totalList.add(total);
				}

			//seller_listでリクエストスコープに登録
			request.setAttribute("seller_list", seller);
			request.setAttribute("total", totalList);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			cmd = "logout";

		} finally {
			if (error == null) {
				//sellerList.jspへフォワードする
				request.getRequestDispatcher("/view/sellerList.jsp").forward(request, response);

				//error.jspへフォワードする
			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

		}

	}

}