<%@page contentType="text/html; charset=UTF-8"%>
<%@page
	import="java.util.ArrayList,bean.Merchandise,bean.Purchase,dao.MerchandiseDAO"%>

<!DOCTYPE html>
<html lang="ja">

<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<title>購入履歴 - フリマアプリ</title>
</head>
<body>
	<!-- ヘッダー -->
	<%@ include file="/common/header.jsp"%>

	<main>
		<div class="container">
			<h2>購入品リスト</h2>

			<section class="product-list">

				<%
				ArrayList<Purchase> list = (ArrayList<Purchase>) request.getAttribute("list");
				ArrayList<Merchandise> merchandiseList = (ArrayList<Merchandise>) request.getAttribute("merchandiseList");
				int count = 0;

				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
				%>

				<div class="product-item">
					

					<img src="<%=request.getContextPath()%><%=merchandiseList.get(i).getPhoto()%>">
					
					<%
					int deposit_status = list.get(i).getDeposit_status();
					if (deposit_status == 1) {
					%>
					<p>入金状況：支払い済み
					<%
					} else {
					%>
					<p>入金状況：未入金
					<%
					}
					%>

					


					<p>商品名</p>
					<%=merchandiseList.get(i).getProduct_name()%>
					<p>金額</p>
					<%=merchandiseList.get(i).getPrice()%>
					<p><a
						href="<%=request.getContextPath()%>/goodsDetail?merchandise_id=<%=merchandiseList.get(i).getMerchandise_id()%>">
						購入品詳細 </a><br>
				</div>
				<%
				++count;
				if (count == 3) {
				%>

				<%
				count = 0;
				}
				%>

				<%
				}
				}
				%>

			</section>
		</div>
	</main>
	<%--フッター --%>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>