<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.User"%>


<%
User user = (User) request.getAttribute("userinfo");
%>

<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報 - フリマアプリ</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>
	<!-- ヘッダー -->
	<%@ include file="/common/header.jsp"%>


	<main>
		<div class="container">
			<h2>ユーザー情報</h2>

			<form action="<%=request.getContextPath()%>/view/userInfoUpdate.jsp"
				method="post">
				<%--コンテキストパスを後で変える --%>


				<label for="username">名前:</label>
				<%--user.getName()--%>
				<p>山田</p><%--　←後で消す --%>
				

				<label for="nickname">ニックネーム:</label>
				<%--user.getNickname()--%>
				<p>yama</p><%--　←後で消す --%>


				<label for="address">メールアドレス:</label>
				<%--user.getEmail()--%>
				<p>aaa@sss</p><%--　←後で消す --%>

				<label for="address">住所:</label>
				<%--user.getAddress()--%>
				<p>東京都新宿区</p><%--　←後で消す --%>



				<button type="submit">更新</button>
			</form>
		</div>
	</main>


	<%--フッター --%>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>