<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.Merchandise,bean.Purchase"%>

<%
Merchandise merchandise = (Merchandise) request.getAttribute("merchandise");
Purchase purchase = (Purchase) request.getAttribute("purchase");

%>

<html>

<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<title>購入品詳細</title>
</head>

<body>
	<!-- ヘッダー -->
	<%@ include file="/common/header.jsp"%>
	<main>
		<div class="container">

			<h2>購入品詳細</h2>

			<div class="product-detail">

				<img src="<%=request.getContextPath()%><%=merchandise.getPhoto()%>">
				<h3>商品名</h3>
				<p><%=merchandise.getProduct_name()%></p>
				<h3>価格</h3>
				<p><%=merchandise.getPrice()%></p>
				<h3>購入日時</h3>
				<p><%=purchase.getPurchase_day()%></p>
				<h3>支払方法</h3>
				<%
				String payment_method = "入金方法が設定せれていません。";

				if (purchase.getPayment_method() == 1) {
					payment_method = "クレジットカード";

				} else if (purchase.getPayment_method() == 2) {
					payment_method = "銀行振込";

				} else if (purchase.getPayment_method() == 3) {
					payment_method = "代金引換";

				}
				%>
				<p><%=payment_method%></p>

				<h3>
					入金状況：
					<%
				String deposit_status = "入金状況";
				if (purchase.getDeposit_status() == 2) {
					deposit_status = "未入金";

				} else {
					deposit_status = "入金済み";

				}
				%>
					<%=deposit_status%></h3>
					<%
					if (purchase.getDeposit_status() == 2) {
					%>
				<form action="<%=request.getContextPath()%>/depositStatus">
					<input type="hidden" name="purchase_id"
						value="<%=purchase.getPurchase_id()%>">
					<p><input type="submit" value="入金完了"></form>
				<%
				}
				%>
				

				<form action="<%=request.getContextPath()%>/message">
				 	<input type="hidden" value="<%=merchandise.getMerchandise_id()%>" name="merchandise_id">
					<input type="submit" value="出品者へ連絡">
				</form>
			</div>
		</div>
	</main>

	<footer>
		<div class="container">
			<p>&copy; 2024 FeeMa</p>
		</div>
	</footer>





</body>

</html>