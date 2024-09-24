package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.Merchandise;
import bean.Purchase;
import bean.User;
import dao.MerchandiseDAO;
import dao.PurchaseDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/purchaseHistory")

public class PurchaseHistoryServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 変数の初期化
		String error = "";

		String cmd = "";
		
		// 配列宣言
		ArrayList<Purchase> list = new ArrayList<Purchase>();
		
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
			
			PurchaseDAO purchaseObjDao = new PurchaseDAO();
			MerchandiseDAO merchandiseObjDao = new MerchandiseDAO();
			
			// 購入品を確認するためのメソッドの呼び出し
			list = purchaseObjDao.userSelectByPurchase("" + user.getUser_id());
			
			for(int i = 0 ; i < list.size() ; i++) {
				int merchandise = list.get(i).getMerchandise_id();
				Merchandise merchandiseInfo = merchandiseObjDao.selectByMerchandise_id("" + merchandise);
				merchandiseList.add(merchandiseInfo);
				
			}
			
		}catch (IllegalStateException e) {

			error = "DB接続エラーの為、購入履歴の確認は行えません。";
			
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
				request.setAttribute("merchandiseList", merchandiseList);
				
				// 一覧画面に遷移
				request.getRequestDispatcher("/view/purchaseHistory.jsp").forward(request, response);
				
			}

		}

	}

}
