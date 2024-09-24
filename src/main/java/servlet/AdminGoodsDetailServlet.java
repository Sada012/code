package servlet;

import java.io.IOException;

import bean.Merchandise;
import dao.MerchandiseDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/adminGoodsDetail")

public class AdminGoodsDetailServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//エラー文の初期化
		String error = null;
		String cmd = null;

		try {
			//UserDAOクラスをインスタンス化
			MerchandiseDAO merchandiseDaoObj = new MerchandiseDAO();

			//Userのインスタンス化
			Merchandise merchandise = new Merchandise();

			//merchandise_idを取り出してその情報をゲットする
			String merchandise_id = request.getParameter("merchandise_id");
			merchandise = merchandiseDaoObj.selectByMerchandise_id(merchandise_id);
			
			//リクエストスコープ
			request.setAttribute("merchandise", merchandise);
			
			
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			cmd = "logout";

		}finally {
			if(error == null) {
				//adminGoodsDetail.jspにフォワード
				request.getRequestDispatcher("/view/adminGoodsDetail.jsp").forward(request, response);
			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			}
		}

	}

}