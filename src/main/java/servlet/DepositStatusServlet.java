package servlet;

import java.io.IOException;

import bean.Purchase;
import bean.User;
import dao.PurchaseDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/depositStatus")
public class DepositStatusServlet extends HttpServlet {

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
			
			// セッションが切れていた場合
			if(user == null) {
							
				error = "セッションが切れているため、ユーザー情報が表示できません！";
							
				cmd = "login";
							
			}
			
			
			//PurchaseDAOオブジェクトの生成
			PurchaseDAO purchaseDao = new PurchaseDAO();
			
			String purchase_id = request.getParameter("purchase_id");
			
			Purchase purchase = purchaseDao.selectByPurchase_id(purchase_id);

			purchase.setDeposit_status(1);
			
			purchaseDao.situationUpdate(purchase);
			
			//DBエラー処理
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、出品物リストは表示できません。";
			
			cmd = "logout";
			
		} catch (Exception e){
			error ="予期せぬエラーが発生しました。<br>"+e;
			
		} finally {
			if (error.equals("")) {
				//stockList.jspへフォワードする
				request.getRequestDispatcher("/purchaseHistory").forward(request, response);
				
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
