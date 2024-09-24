<%@page contentType="text/html; charset=UTF-8"%>
<%
String error = (String) request.getAttribute("error");
if (error == null) {
	error = "";
}
%>
<html>
<head>
<title>エラー</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
	</head>
<body>

<!-- ヘッダー -->
	<%@ include file="/common/header.jsp"%>
	<main>
	<center>
		<!-- エラーメッセージ  -->
		<p class="error-msg">●●エラー●●</p>
		<p class="red" class="error-msg"><%=error%></p>
		<br>
		<br>
		<br> <a href="<%=request.getContextPath()%>/goodsList">戻る</a>
	</center>
	</main>
	<%--フッター --%>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>