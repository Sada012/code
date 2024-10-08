<%@page contentType="text/html; charset=UTF-8"%>

<%
String error = (String) request.getAttribute("error");
if (error == null) {
	error = "";
}
String cmd = (String) request.getAttribute("cmd");
if (cmd == "") {
	cmd = "logout";
}
%>

<html>
<head>
<title>エラー画面</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<!-- ブラウザ全体 -->
	<div id="wrap">
		<!-- ヘッダー部分 -->
		<%@ include file="/common/header.jsp"%>

		<!-- メニュー部分 -->
		<div id="menu">
			<div class="container">
				<!-- ページタイトル -->
				<div id="page_title">
					<h2>■■エラー■■</h2>
				</div>
			</div>
		</div>

		<!-- コンテンツ部分 -->
		<div id="main" class="container">
			<!-- エラーメッセージ  -->
			<p class="error-msg">●●エラー●●</p>
			<p class="red" class="error-msg"><%=error%></p>

			<!-- リンク先  -->
			<p class="back-link">
				<%
				if (cmd.equals("logout")) {
				%>
				<th style="text-align: center">[<a
					href="<%=request.getContextPath()%>/logout">ログアウト</a>]
				</th>
				<%
				} else if (cmd.equals("list")) {
				%>
				<th style="text-align: center">[<a
					href="<%=request.getContextPath()%>/list">一覧表示に戻る</a>]
				</th>
				<%
				} else if (cmd.equals("menu")) {
				%>
				<th style="text-align: center">[<a
					href="<%=request.getContextPath()%>/view/menu.jsp">メニューに戻る</a>]
				</th>
				<%
				} else if (cmd.equals("listUser")) {
				%>
				<th style="text-align: center">[<a
					href="<%=request.getContextPath()%>/listUser">一覧表示に戻る</a>]
				</th>
				<%
				}
				%>
			</p>
		</div>

		<!-- フッター部分 -->
		<%@ include file="/common/footer.jsp"%>

	</div>
</body>
</html>