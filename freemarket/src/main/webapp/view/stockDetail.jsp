<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.Merchandise,bean.User"%>

<%
Merchandise merchandise = (Merchandise) request.getAttribute("merchandise");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出品物詳細 - フリマアプリ</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>

	<!-- ヘッダー -->
	<%@ include file="/common/header.jsp"%>

	<center>
		<main>
			<div class="container">
				<div class="product-detail">
					<h2>出品物詳細</h2>

					<img src="<%=request.getContextPath()%><%=merchandise.getPhoto()%>">
					<h3>
						商品名:<%= merchandise.getProduct_name() %></h3>
					<p><%=merchandise.getProduct_name()%></p>

					<h3>価格:</h3>
					<p><%=merchandise.getPrice()%></p>

					<h3>出品日時:</h3>
					<p><%= merchandise.getListing_date() %>
					</p>

					<h3>出品地域:</h3>
					<p>東京都</p>

					<h3>備考：</h3>
					<p><%= merchandise.getRemarks() %></p>
					<a
						href="<%=request.getContextPath()%>/view/stockUpdate.jsp?merchandise_id=<%=merchandise.getMerchandise_id()%>"
						class="button">出品情報を更新</a>
				</div>
			</div>
		</main>

		<footer>
			<div class="container">
				<p>&copy; 2024 フリマアプリ</p>
			</div>
		</footer>
</body>
</html>