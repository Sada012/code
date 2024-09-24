package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.Merchandise;
import bean.Purchase;
import bean.User;
import dao.MerchandiseDAO;
import dao.PurchaseDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/adminUserDetail")

public class AdminUserDetailServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//エラー文の初期化
		String error = null;
		String cmd = null;

		try {
			UserDAO userDaoObj = new UserDAO();

			User user = new User();

			PurchaseDAO purchaseDaoObj = new PurchaseDAO();

			String user_id = request.getParameter("user_id");
			
			user = userDaoObj.selectByUserInfo(user_id);

			//user_idが存在しなかった場合エラーにする
			if (user.getName() == null) {
				error = "error";
				request.setAttribute("error", error);
				request.setAttribute("cmd", "adminUserDetail");
				return;
			}

			ArrayList<Purchase> purchaseUser = purchaseDaoObj.userSelectByPurchase(user_id);

			MerchandiseDAO merchandiseDaoObj = new MerchandiseDAO();

			var merchandiseList = new ArrayList<Merchandise>();

			//商品情報の格納
			for (int i = 0; i < purchaseUser.size(); i++) {
				//商品IDの取り出し
				Purchase purchases = (Purchase) purchaseUser.get(i);
				//PurchaseDAOのselectByPurchaseInfoの取り出し
				Merchandise merchandiseInfo = merchandiseDaoObj.selectByMerchandise_id(Integer.toString(purchases.getMerchandise_id()));
				//purchase_dayに格納
				merchandiseList.add(merchandiseInfo);
			}

			//⑥リクエストスコープにuserとして登録
			request.setAttribute("user", user);
			request.setAttribute("purchaseUser", purchaseUser);
			request.setAttribute("merchandiseList", merchandiseList);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			cmd = "logout";
		} finally {
			if (error == null) {
				//stockDetail.jspにフォワード
				request.getRequestDispatcher("/view/adminUserDetail.jsp").forward(request, response);
			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);

				request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			}
		}

	}

}