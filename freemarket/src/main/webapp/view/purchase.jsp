<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Purchase,bean.Merchandise"%>

<%
Merchandise merchandise = (Merchandise)request.getAttribute("merchandise");
%>

<html>
<head>
<meta charset="UTF-8">
<title>購入確認 - フリマアプリ</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>
	<!-- ヘッダー -->
	<%@ include file="/common/header.jsp"%>

	<main>
		<div class="container">
			<h2>購入確認</h2>

			<form action="<%=request.getContextPath()%>/recipt"
				method="post">
			<tr>
				<td style="width: 150px; font-weight: 600; font-size: x-large">商品</td>
				<td style="font-size: x-large"><%=merchandise.getProduct_name()%></td>
			</tr>

			<tr>
				<td style="width: 150px; font-weight: 600; font-size: x-large">金額</td>
				<td style="font-size: x-large"><%=merchandise.getPrice()%></td>
			</tr>
		</table>

		<p style="margin-bottom: 2em;"></p>

		<label for="payment">支払い方法を選択してください</label> <select id="payment"
					name="payment" required>
					<option value="1">クレジットカード</option>
					<option value="2">銀行振込</option>
					<option value="3">代金引換</option>
				</select>
				<input type="hidden" value="<%=merchandise.getMerchandise_id() %>" name="merchandise_id" >
				<button type="submit">購入する</button>
			</form>
		</div>
	</main>


	<%--フッター --%>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>