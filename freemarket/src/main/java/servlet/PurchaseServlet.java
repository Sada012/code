package servlet;

import java.io.IOException;

import bean.Merchandise;
import dao.MerchandiseDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/purchase")
public class PurchaseServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// エラーメッセージとcmdを格納
		String error = "";
		String cmd = "";
		
		//merchandise_idの情報を受け取る
		String merchandise_id = request.getParameter("merchandise_id");

		try {
			// MerchandiseDAOクラスのオブジェクト
			MerchandiseDAO merchandiseDao = new MerchandiseDAO();
			
			// 購入する商品のリストを取得
			Merchandise merchandise = new Merchandise();
			
		
			merchandise = merchandiseDao.selectByMerchandise_id(merchandise_id);

		
		//リクエストスコープにgoods_listを格納する
		request.setAttribute("merchandise", merchandise);


		} catch (Exception e) {
			// DB接続エラーの場合
			error = "DB接続エラーの為、購入状況確認は出来ません。";
			cmd = "logout";
		} finally {
			if (cmd.isEmpty()) {
				// エラーがない場合、purchase.jspへフォワード
				request.getRequestDispatcher("/view/purchase.jsp").forward(request, response);
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