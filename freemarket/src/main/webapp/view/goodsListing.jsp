<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="bean.Merchandise"%>

<html>
<head>
<title>商品出品</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<!-- ヘッダー -->
	<%@ include file="/common/header.jsp"%>

	<%--入力画面の表示 --%>
	<main>
		<div class="container">
			<h2>-商品の出品-</h2>
			<form action="<%=request.getContextPath()%>/goodsListing"
				enctype="multipart/form-data" method="post">
				<input type="file" name="photo" class="input-file" accept="image/*" required > 
				<label for="product_name">商品名:</label> 
				<input type="text" name="product_name" required>
				<label for="price">価格:</label>
				<input type="text" name="price" required> 
				<label for="kinds">種類:</label>
				<input type="text" name="kinds" required> 
				<label for="remarks">商品の説明:</label>
				<input type="text" name="remarks" required>
				<br>
				<button type="submit">出品する</button>
			</form>
		</div>
	</main>

	<%--フッター --%>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>