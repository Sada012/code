/*
 * プログラミング名：GoodsDetailServlet
 * プログラムの説明：商品詳細機能
 * 作成者：
 * 作成日：2024年6月25日
 */

package servlet;

import java.io.IOException;
import java.util.ArrayList;

import bean.Favorite;
import bean.Merchandise;
import bean.Purchase;
import bean.User;
import dao.FavoriteDAO;
import dao.MerchandiseDAO;
import dao.PurchaseDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/goodsDetail")

public class GoodsDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 変数の初期化
		String error = "";

		String cmd = "";

		// Merchandiseクラスのインスタンス化
		Merchandise merchandise = new Merchandise();

		String detailedTransition = "";
		String favorite = "";

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

			// 送信された商品情報の受け取り
			String merchandise_id = request.getParameter("merchandise_id");

			// MerchandiseDAOクラスのインスタンス化
			MerchandiseDAO merchandiseDaoObj = new MerchandiseDAO();

			//PurchaseDAOクラスのインスタンス化
			PurchaseDAO purchaseDaoObj = new PurchaseDAO();

			//PurchaseDAOのselectByPurchaseメソッドの呼び出し
			Purchase purchase = purchaseDaoObj.selectByPurchase(merchandise_id);

			// 商品の詳細を表示させるメソッドの呼び出し
			merchandise = merchandiseDaoObj.selectByMerchandise_id(merchandise_id);

			//管理者であればadminGoodsDetail
			if (user.getAuthority() == 1) {
				detailedTransition = "adminGoodsDetail";

				//出品者であればstockDetail
			} else if (merchandise.getUser_id() == user.getUser_id()) {
				detailedTransition = "stockDetail";

				//購入者であればpurchaseDetail
			} else if (purchase.getUser_id() == user.getUser_id()) {
				detailedTransition = "purchaseDetail";
				request.setAttribute("purchase", purchase);
			} else {

				FavoriteDAO favoriteDao = new FavoriteDAO();
				ArrayList<Favorite> favorited = new ArrayList<Favorite>();

				favorited = favoriteDao.selectByFavorite(Integer.parseInt(merchandise_id));

				for (int i = 0; i < favorited.size(); i++) {
					if (favorited.get(i).getUser_id() == user.getUser_id()) {
						favorite = "お気に入り";
						request.setAttribute("favorite", favorite);
					}

				}

			}

			// リクエストスコープに登録
			request.setAttribute("merchandise", merchandise);
			

		} catch (IllegalStateException e) {

			error = "DB接続エラーの為、商品詳細の表示は行えません。";

			cmd = "login";

		} catch (Exception e) {

			error = "予期せぬエラーが発生しました。 <br>" + e;

			cmd = "login";

		} finally {

			if (error != "") {

				// リクエストスコープに登録
				request.setAttribute("error", error);

				request.setAttribute("cmd", cmd);

				// error画面に遷移
				request.getRequestDispatcher("/view/error.jsp").forward(request, response);

			} else {

				if (detailedTransition.equals("stockDetail")) {
					//出品物詳細画面に遷移
					request.getRequestDispatcher("/view/stockDetail.jsp").forward(request, response);

				} else if (detailedTransition.equals("adminGoodsDetail.jsp")) {
					//管理者出品物詳細画面に遷移
					request.getRequestDispatcher("/view/adminGoodsDetail.jsp").forward(request, response);

				} else if (detailedTransition.equals("purchaseDetail")) {
					//購入品詳細画面に遷移
					request.getRequestDispatcher("/view/purchaseDetail.jsp").forward(request, response);

				} else {
					// 商品詳細画面に遷移
					request.getRequestDispatcher("/view/goodsDetail.jsp").forward(request, response);

				}

			}

		}

	}

}
