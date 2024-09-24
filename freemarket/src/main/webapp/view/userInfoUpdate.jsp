<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.User"%>
<%@page import="util.MyFormat"%>



<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報更新 - フリマアプリ</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>
	<!-- ヘッダー -->
	<%@ include file="/common/header.jsp"%>


	<main>
		<div class="container">
			<h2>ユーザー情報更新</h2>
			
			<form action="<%=request.getContextPath()%>/userInfoUpdate"
			 method="post">


				<label for="username">名前:</label> 
				<input type="text" id="username" name="name" value="<%=user.getName()%>" required> 
				<label for="nickname">ニックネーム:</label> 
				<input type="text" id="nickname" name="nickname" value="<%=user.getNickname()%>" required>
				<label for="address">メールアドレス:</label>
				<input type="text" id="email" name="email" value="<%=user.getEmail()%>" required>
				<label for="address">住所:</label> 
				<input type="text" id="adress" name="address" value="<%=user.getAddress()%>" required>
				<button type="submit">変更</button>

			</form>
		</div>
	</main>


	<%--フッター --%>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>