<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.User,bean.Merchandise,bean.Purchase"%>


<%
Merchandise merchandise = (Merchandise) request.getAttribute("merchandise");
Purchase purchase = (Purchase) request.getAttribute("purchase");
%>

<html>
<head>
<meta charset="UTF-8">
<title>購入明細 - フリマアプリ</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>
	<! ヘッダー >
	<%@ include file="/common/header.jsp"%>


	<main>
		<div class="container">
			<h2>購入明細</h2>

			<h4>商品を購入しました。ご利用ありがとうございました。</h4>

			<form action="<%=request.getContextPath()%>/view/purchaseHistory.jsp"
				method="post">

				<p>
					商品名:
					<%=merchandise.getProduct_name()%>

				</p>

				<p>
					価格:
					<%=merchandise.getPrice()%>

				</p>

				<p>
					配送先:
					<%//merchandise.getPrice()%>
					東京都
				</p>

				<p>
					支払い方法:
					<%
					String payment_method = "お支払方法を選択していません";
					if(purchase.getPayment_method() == 1){
						payment_method = "クレジットカード払い";
						
					}else if(purchase.getPayment_method() == 2){
						payment_method = "電子マネー払い";
						
					}else if(purchase.getPayment_method() == 3){
						payment_method = "コンビニ払い";
						
					}
					%>
					<%=payment_method %>
				</p>

				<p>
					購入日時:
					<%=purchase.getPurchase_day()%>
				</p>

				<center>
					<p class="product-item">お支払い後、入金状況を確定してください。</p>
				</center>

			</form>
		</div>
	</main>


	<%--フッター --%>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>