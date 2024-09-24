<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.User"%>

<html>
<head>
<title>ユーザー一覧</title>

<%--CSS接続 --%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">

</head>
<body>
	<!-- ヘッダー -->
	<%@ include file="/common/header.jsp"%>


	<%--一覧表示--%>
	<main>
		<div class="container">
			<table class="table_style">
				<h2>ユーザー一覧</h2>
				<%--カラム名 --%>
				<tr>
					<th class="table_name">ID</th>
					<th class="table_name">名前</th>
					<th class="table_name">ニックネーム</th>
					<th class="table_name">メールアドレス</th>
					<th class="table_name">権限</th>
				</tr>

				<%
			//リクエストスコープのseller_listを取り出す
			ArrayList<User> user_list = (ArrayList<User>) request.getAttribute("user_list");

				//userに入っている場合
			if (user_list != null) {
				
				//繰り返しを使用しすべてのリストを表示する
				for (int i = 0; i < user_list.size(); i++) {
					
					//booksでi番目のbookを取り出す
					User users = (User) user_list.get(i);
			%>

				<tr>
					<%--ID --%>
					<td class="table_data"><a
						href="<%=request.getContextPath()%>/adminUserDetail?user_id=<%=users.getUser_id()%>&cmd=detail"><%=users.getUser_id()%></a></td>

					<%--名前 --%>
					<td class="table_data"><%=users.getName()%></td>

					<%--ニックネーム --%>
					<td class="table_data"><%=users.getNickname() %></td>

					<%--メールアドレス --%>
					<td class="table_data"><%=users.getEmail() %></td>

					<%--権限 --%>
					<td class="table_data"><%=users.getAuthority() %></td>
				</tr>

				<%
				}
			} 
			%>
			</table>
	</main>


	<%--フッター --%>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>