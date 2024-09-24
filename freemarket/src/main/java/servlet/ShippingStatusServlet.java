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

@WebServlet("/shippingStatus")
public class ShippingStatusServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//エラーメッセージとcmdを格納
		String error = "";
		String cmd = "";
		
		// セッションオブジェクトの生成
		HttpSession session = request.getSession();

		try {

			// ユーザー情報の取得
			User user = (User) session.getAttribute("user");

			//String email = (String)session.getAttribute("email");

			// セッションが切れていた場合
			if (user == null) {

				error = "セッションが切れているため、ユーザー情報が表示できません！";

				cmd = "login";

			}

			//PurchaseDAOオブジェクトの生成
			PurchaseDAO purchaseDao = new PurchaseDAO();

			//Purchase purchase = new Purchase();
			
			String merchandise_id = request.getParameter("merchandise");
			
			Purchase purchase = purchaseDao.selectByPurchase(merchandise_id);

			purchase.setTrading_status(1);
			
			MerchandiseDAO merchandiseDao = new MerchandiseDAO();
			Merchandise merchandise = merchandiseDao.selectByMerchandise_id(merchandise_id);

			User sender = new User();
			UserDAO userDao = new UserDAO();

			sender = userDao.selectByUser_id(purchase.getUser_id());
			
			//メール処理
			SendMail mail = new SendMail();
			mail.post(sender, merchandise, 1);

			purchaseDao.situationUpdate(purchase);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、発送状況は更新できません。";

			cmd = "logout";

		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。<br>" + e;

		} finally {
			if (error.equals("")) {
				//stockList.jspへフォワードする
				request.getRequestDispatcher("/view/stockList.jsp").forward(request, response);

			} else {
				//リクエストスコープにエラーメッセージを登録する
				request.setAttribute("error", error);

				//リクエストスコープにcmdを登録する
				request.setAttribute("errorCmd", cmd);

				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}

	}

}
