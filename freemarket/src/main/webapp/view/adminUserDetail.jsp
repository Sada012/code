<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="bean.User,bean.Merchandise,bean.Purchase,java.util.ArrayList"%>

<%
User userInfo = (User) request.getAttribute("user");
ArrayList<Purchase> purchaseUser = (ArrayList<Purchase>) request.getAttribute("purchaseUser");
ArrayList<Merchandise> merchandiseList = (ArrayList<Merchandise>) request.getAttribute("merchandiseList");
int total = 0;
%>

<html>
<head>

<title>詳細機能を実装する</title>
<%--CSS接続 --%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

</head>
<body>
	<!-- ヘッダー -->
	<%@ include file="/common/header.jsp"%>
	<main>
	<%--詳細画面--%>
	<div class="mini_blank">
		<table class="table_style">

			<tr>
				<td class="center">ID：<%=userInfo.getUser_id()%>
				</td>

				<td class="center">名前：<%=userInfo.getName() %>
				</td>

				<td class="center">ニックネーム：<%=userInfo.getNickname() %>
				</td>

				<td class="center">メールアドレス：<%=userInfo.getEmail() %>
			</tr>

		</table>
		<hr class="blackLine">
	</div>

	<p>購入した商品</p>

	<table class="table_style">

		<tr>
			<td>商品ID</td>
			<td>商品名</td>
			<td>価格</td>
			<td>購入日</td>
		</tr>
		<%
		for(int i = 0 ; i < purchaseUser.size() ; i++){
			Purchase purchaseUsers = purchaseUser.get(i);
			Merchandise merchandiseLists = merchandiseList.get(i);
		
			%>
		<tr>
			<td><%=merchandiseLists.getMerchandise_id() %></td>
			<td><%=merchandiseLists.getProduct_name() %></td>
			<td><%=merchandiseLists.getPrice() %></td>
			<td><%=purchaseUsers.getPurchase_day() %></td>
		</tr>
		<% if(purchaseUsers.getPurchase_day() != null){
			total += merchandiseLists.getPrice();
			}
		}
		%>
	</table>

	<p>
		合計金額:
		<%= total %></p>
	<form action="<%=request.getContextPath()%>/view/message.jsp">
		<input type="submit" value="連絡"> 
		<input type="hidden" value="<%=userInfo.getUser_id()%>" name="user_id"></td>

	</form>
</main>
	<%--フッター --%>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>