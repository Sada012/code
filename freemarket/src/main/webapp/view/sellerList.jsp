<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.User"%>

<html>
<head>
<title>出品者一覧</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">

</head>

<body>


	<!-- ヘッダー -->
	<%@ include file="/common/header.jsp"%>


	<main>
		<div class="container">
			<table class="table_style">
				<h2>出品者一覧</h2>
				<tr>
					<th class="table_name">ID</th>
					<th class="table_name">名前</th>
					<th class="table_name">売上</th>
				</tr>

				<%
			//リクエストスコープのseller_listを取り出す
			ArrayList<User> seller = (ArrayList<User>) request.getAttribute("seller_list");
			ArrayList<Integer> total = (ArrayList<Integer>) request.getAttribute("total");
			
				//sellerリストに入っている場合
			if (seller != null) {
				
				//繰り返しを使用しすべてのリストを表示する
				for (int i = 0; i < seller.size(); i++) {
					
					//sellersでi番目のsellerを取り出す
					User sellers = (User) seller.get(i);
			%>

				<tr>
					<td class="table_data"><a>
					<a href="<%=request.getContextPath()%>/detailSeller?user_id=<%=sellers.getUser_id()%>&cmd=detail"><%=sellers.getUser_id()%></a></td>

					<td class="table_data"><%=sellers.getName()%></td>
					<td class="table_data"><%=total.get(i)%>円</td>

					<%
				}
			}
			%>
				
			</table>
		</div>
	</main>

	<!-- フッター-->
	<%@ include file="/common/footer.jsp"%>

</body>
</html>