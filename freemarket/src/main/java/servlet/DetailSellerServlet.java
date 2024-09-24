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

@WebServlet("/detailSeller")

public class DetailSellerServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String error = null;
		String cmd = null;

		try {
			//userDAOクラスをインスタンス化
			UserDAO userDaoObj = new UserDAO();

			//MerchandiseDAOクラスのインスタンス化
			MerchandiseDAO marchandiseDaoObj = new MerchandiseDAO();

			//PurchaseDAOクラスのインスタンス化
			PurchaseDAO purchaseDaoObj = new PurchaseDAO();

			//purchase_day用のArrayListを生成
			var purchase_day = new ArrayList<String>();

			String user_id = request.getParameter("user_id");

			User seller = userDaoObj.selectByUserInfo(user_id);

			//MerchandiseDAOクラスのselectAllメソッドを利用
			var sellerDetail = marchandiseDaoObj.userSelectByMerchandise(user_id);

			//売上日の格納
			for (int i = 0; i < sellerDetail.size(); i++) {
				//商品IDの取り出し
				Merchandise merchandise_id = (Merchandise) sellerDetail.get(i);
				
				
				//PurchaseDAOのselectByPurchaseInfoの取り出し
				Purchase purchase = purchaseDaoObj.selectByPurchase(Integer.toString(merchandise_id.getMerchandise_id()));
				//purchase_dayに格納
				purchase_day.add(purchase.getPurchase_day());

			}

			//それぞれをリクエストスコープに登録
			request.setAttribute("seller", seller);
			request.setAttribute("seller_list", sellerDetail);
			request.setAttribute("purchase_day", purchase_day);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			cmd = "logout";

		} finally {
			if (error == null) {
				//sellerList.jspへフォワードする
				request.getRequestDispatcher("/view/detailSeller.jsp").forward(request, response);

				//error.jspへフォワードする
			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}

		}

	}
}
