<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Merchandise"%>

<%
ArrayList<Merchandise> list = (ArrayList<Merchandise>) request.getAttribute("stock_list");
int count = 0;
%>

<html>
<head>
<meta charset="UTF-8">
<title>出品物リスト - フリマアプリ</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>

	<!-- ヘッダー -->
	<%@ include file="/common/header.jsp"%>

	<main>

		<h2>出品物リスト</h2>
		<div class="container">
		<section class="product-list">
			
			<%
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					Merchandise merchandise = (Merchandise) list.get(i);
			%>
			
				<div class="product-item">
					<img src="<%=request.getContextPath()%><%=merchandise.getPhoto()%>">
					<h3>
					商品名
						<%=merchandise.getProduct_name()%>
						
					</h3>
					<p>価格
						<%=merchandise.getPrice()%>
					</p>
					<a href="<%=request.getContextPath()%>/goodsDetail?merchandise_id=<%=merchandise.getMerchandise_id()%>"
						class="button">詳細を見る</a>
				</div>

				<%
				count++;
				if (count == 3) {
					count = 0;
				%>

				<br>
				<%
				}
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