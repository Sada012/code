<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.User"%>



<html>
<meta charset="UTF-8">

<head>

<title>新規登録</title>
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
			<%=user.getName()%>

			

			<label for="nickname">ニックネーム:</label>
			<%=user.getNickname()%>



			<label for="address">メールアドレス:</label>
			<%=user.getEmail()%>


			<label for="address">住所:</label>
			<%=user.getAddress()%>




			<button type="submit">更新</button>
		</form>
	</div>
	</main>


	<%--フッター --%>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>