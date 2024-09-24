<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>マイページ</title>
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
				<h2>-マイページ-</h2>
				<br>
				<a href="<%=request.getContextPath()%>/userInfo">ユーザー情報</a>
				<br>

				<br>

				<a href="<%=request.getContextPath()%>/goodsList">商品一覧</a>
				<br>

				<br>

				<a href="<%=request.getContextPath()%>/view/goodsListing.jsp">商品の出品</a>
				<br>

				<br>

				<a href="<%=request.getContextPath()%>/stockList">出品物一覧</a>
				<br>

				<br>

				<a href="<%=request.getContextPath()%>/purchaseHistory">購入履歴</a>
				<br>

				<br>
				<a href="<%=request.getContextPath()%>/favoriteList">お気に入りリスト</a>
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