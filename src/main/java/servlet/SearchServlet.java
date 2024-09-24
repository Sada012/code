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

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String error = null;
		String errorCmd = null;

		try {
			//①BookDAOクラスのオブジェクトを生成します。
			MerchandiseDAO objMerchandiseDao = new MerchandiseDAO();

			//③画面から送信される検索条件を受け取ります。
			String product_name = request.getParameter("product_name");
			String kinds = request.getParameter("kinds");
			String remarks = request.getParameter("remarks");

			//④BookDAOクラスに定義したsearch（）メソッドを利用して書籍情報を取得します。
			ArrayList<Merchandise> goods_list = new ArrayList<Merchandise>();
			goods_list = objMerchandiseDao.search(product_name, kinds, remarks);

			//⑤取得した書籍情報を「book_list」という名前でリクエストスコープに登録します。
			request.setAttribute("goods_list", goods_list);
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、一覧表示は行えませんでした。";
			errorCmd = "logout";

		} catch (Exception e) {
			error = "例外が起きました" + e;

		} finally {

			//エラーがない場合一覧表示
			if (error == null) {

				//⑥「ListServlet」へフォワード
				request.getRequestDispatcher("/view/goodsList.jsp").forward(request, response);

				//エラーがある場合エラー画面の表示
			} else {

				request.setAttribute("error", error); //エラーをセットする
				request.setAttribute("errorCmd", errorCmd); //cmdをセットする

				//エラー画面へフォワード
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);//⑥「list.jsp」へフォワードします。

			}

		}
	}
}
