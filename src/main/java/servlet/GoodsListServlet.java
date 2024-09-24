/*
 * プログラミング名：GoodsList
 * プログラムの説明：商品一覧
 * 作成者：
 * 作成日：2024年6月20日
 */

package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.Merchandise;
import dao.MerchandiseDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/goodsList")
public class GoodsListServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* 変数の初期化 */
		String error = "";
		String cmd = "";

		try {
			// 一覧の並びを取得
			String sort = request.getParameter("sort");
			if (sort == null) {
				sort = "ascending";
			}

			// MerchandiseDAOクラスのオブジェクトを生成
			MerchandiseDAO merchandiseObjDao = new MerchandiseDAO();

			// 昇順をデフォルトとして引数に置き、商品をテーブルから取得する
			ArrayList<Merchandise> goods_list = merchandiseObjDao.selectAll(sort);

			// リクエストスコープ
			request.setAttribute("goods_list", goods_list);

		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			cmd = "logout";
		} catch (Exception e) {
			error = "予期せぬエラーが発生しました。<br>" + e;
		} finally {
			if (error.equals("")) {
				request.getRequestDispatcher("/view/goodsList.jsp").forward(request, response);
			} else {
				// error.jspにフォワード
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				//フォワード先の指定
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}
}