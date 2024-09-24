<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.Merchandise"%>

<%
Merchandise merchandise = (Merchandise) request.getAttribute("merchandise");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者商品詳細</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>

	<!-- ヘッダー -->
	<%@ include file="/common/header.jsp"%>
	<main>
	
		<div class="container">
			<div class="product-detail">
				<img src="<%=request.getContextPath()%><%=merchandise.getPhoto()%>">
				<h3>商品名</h3>
				<p><%=merchandise.getProduct_name()%></p>

				<h3>種類</h3>
				<p><%=merchandise.getKinds()%></p>

				<h3>価格</h3>
				<p><%=merchandise.getPrice()%></p>

				<h3>備考</h3>
				<p><%=merchandise.getRemarks()%></p>

				<h3>販売状況</h3>
				<%
				if (merchandise.getSales_status() == 1) {
				%>
				<td>販売中</td>
				<%
				} else if (merchandise.getSales_status() == 2) {
				%>
				<td>Sold Out</td>
				<%
				}
				%>
				</tr>

				<table>
					<tr>
						<form action="<%=request.getContextPath()%>/message">

							<td><input type="submit" value="連絡"> <input
								type="hidden" value="<%=merchandise.getUser_id()%>"
								name="user_id"></td>

						</form>
						<%
						if (merchandise.getSales_status() == 1) {
						%>
						<form action="<%=request.getContextPath()%>/deleteItem">
							<td><input type="submit" value="削除"> <input
								type="hidden" value="<%=merchandise.getMerchandise_id()%>"
								name="merchandise_id"></td>
						</form>
						<%
						}
						%>
					</tr>

				</table>
			</div>
		</div>
	</main>

	<%--フッター --%>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>