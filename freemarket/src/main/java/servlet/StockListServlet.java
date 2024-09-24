package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.Merchandise;
import bean.User;
import dao.MerchandiseDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/stockList")
public class StockListServlet extends HttpServlet {

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
			
			// 配列宣言
			ArrayList<Merchandise> stock_list = new ArrayList<Merchandise>();
			
			//MerchandiseDAOオブジェクトの生成
			MerchandiseDAO merchandiseDao = new MerchandiseDAO();
			
			// 一覧の並びを取得
			String sort = request.getParameter("sort");
			if (sort == null) {
				sort = "ascending";
			}
			
			// 出品商品を確認するためのメソッドの呼び出し
			stock_list = merchandiseDao.selectAll(sort, user.getUser_id());
			
			
			//取得した出品リストを「stock_list」という名前でリクエストスコープに登録
			request.setAttribute("stock_list", stock_list);
			
			
			//DBエラー処理
		} catch (IllegalStateException e) {
			error = "DB接続エラーの為、出品物リストは表示できません。";
			
			cmd = "logout";
			
		} catch (Exception e){
			error ="予期せぬエラーが発生しました。<br>"+e;
			
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
