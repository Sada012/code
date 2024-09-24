package servlet;

import java.io.IOException;

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
import jakarta.servlet.http.HttpSession;
import util.SendMail;

@WebServlet("/recipt")
public class ReciptServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// エラーメッセージとcmdを格納
		String error = "";
		String cmd = "";

		//merchandise_idの情報を受け取る
		String merchandise_id = request.getParameter("merchandise_id");
		String payment = request.getParameter("payment");

		try {

			// セッションスコープの保存領域を確保
			HttpSession session = request.getSession();

			// ユーザー情報の取得
			User user = (User) session.getAttribute("user");

			//セッション切れか確認
			if (user == null) {
				//セッション切れならerror.jspへフォワード
				error = "セッション切れの為、詳細の確認が出来ません。";
				cmd = "logout";

				return;
			}

			// PurchaseDAOをインスタンス化
			PurchaseDAO purchaseDao = new PurchaseDAO();

			// MerchandiseDAOクラスのオブジェクト
			MerchandiseDAO merchandiseDao = new MerchandiseDAO();

			//購入状況を呼び出す
			Merchandise merchandise = merchandiseDao.selectByMerchandise_id(merchandise_id);

			if (merchandise.getMerchandise_id() == 0) {
				error = "購入品が存在しないため、購入出来ません。";
				cmd = "mypage";

			}

			Purchase purchase = new Purchase();
			purchase.setMerchandise_id(Integer.parseInt(merchandise_id));
			purchase.setUser_id(user.getUser_id());
			purchase.setTrading_status(2);
			purchase.setDeposit_status(2);
			purchase.setPayment_method(Integer.parseInt(payment));

			merchandise.setSales_status(2);
			
			// DBに注文情報を登録
			purchaseDao.insert(purchase);
			merchandiseDao.update(merchandise);
			
			Purchase purchases = purchaseDao.selectByPurchase(merchandise_id);
			
			User sender = new User();
			UserDAO userDao = new UserDAO();
			
			sender = userDao.selectByUser_id(merchandise.getUser_id());
			
			SendMail mail = new SendMail();
			mail.post(sender, merchandise, 0);

			// 購入情報をリクエストスコープにセット
			request.setAttribute("purchase", purchases);
			request.setAttribute("merchandise", merchandise);

		} catch (Exception e) {

			// DB接続エラーの場合
			error = "DB接続エラーの為、購入明細画面は出来ません。";
			cmd = "logout";
		} finally {
			if (cmd.isEmpty()) {
				// エラーがない場合、purchase.jspへフォワード
				request.getRequestDispatcher("/view/recipt.jsp").forward(request, response);
			} else {
				// エラーメッセージをリクエストスコープに登録
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				// error.jspへフォワード
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}

}
