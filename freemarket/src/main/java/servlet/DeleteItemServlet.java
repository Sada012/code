package servlet;

import java.io.IOException;

import bean.Merchandise;
import dao.MerchandiseDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteItem")
public class DeleteItemServlet extends HttpServlet {
	public void doGet(HttpServletRequest request ,HttpServletResponse response)
			throws ServletException ,IOException {
		String error = "";
		String cmd = "list";
		try {
			//BookDAOクラスのオブジェクトを生成
			MerchandiseDAO merchandiseDAO = new MerchandiseDAO();

			Merchandise merchandise = new Merchandise();

			// 送信されたISBN情報を受け取る
			String merchandise_id = request.getParameter("merchandise_id");

			merchandise = merchandiseDAO.selectByMerchandise_id(merchandise_id); 

			// 指定されたIsbnがデータにない場合、エラーを起こす処理
			if(merchandise == null){
				error = "削除対象の商品が存在しない為、書籍削除処理は行えませんでした。";
			} else {
				// deleteメソッドを呼び出す
				merchandiseDAO.delete(merchandise_id);
			}
		} catch (IllegalStateException e) {
			error ="DB接続エラーの為、商品削除処理はできませんでした。";
			cmd = "logout";
		} catch (Exception e) {
			error ="予期せぬエラーが発生しました。<br>"+e;
		} finally {
			if (error.equals("")) {
				//フォワード先の指定
				request.getRequestDispatcher("/goodsList").forward(request, response);
			} else {
				request.setAttribute("error", error);
				request.setAttribute("cmd", cmd);
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);
			}
		}
	}

}