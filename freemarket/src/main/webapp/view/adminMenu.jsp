<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>管理者メニュー</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>
	<!-- ヘッダー -->
	<%@ include file="/common/header.jsp"%>
<main>
	<div style="text-align: center;">
		<table style="margin: auto">
			<tr>
				<h2>-管理者メニュー-</h2>
				<br>
				<a href="<%=request.getContextPath()%>/sellerList">出品者一覧</a>
				<br>
				<br>
				<a href="<%=request.getContextPath()%>/userList">ユーザー一覧</a>
				<br>
				<br>
				<a href="<%=request.getContextPath()%>/goodsList">商品一覧</a>
				<br>
				<br>
				<a  href="<%=request.getContextPath()%>/mailBox">メール一覧</a>
				<br>
				<br>
				<a href="<%=request.getContextPath()%>/view/adminInfoUpdate.jsp">管理者情報更新</a>
				<br>
				<br>
				<a href="<%=request.getContextPath()%>/view/adminSignUp.jsp">新規登録</a>
				<br>
				<br>
				<a href="<%=request.getContextPath()%>/logout">[ログアウト]</a>
			</tr>
		</table>
	</div>
	</main>
	<%--フッター --%>
	<%@ include file="/common/footer.jsp"%>
</body>

</html>