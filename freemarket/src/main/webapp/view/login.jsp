<%@page contentType="text/html; charset=UTF-8"%>
<%@page import= "java.net.*, bean.User"%>
<% 
String error = (String) request.getAttribute("error");
if(error == null){
	error = "";
}
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ログイン - フリマアプリ</title>
    <link rel="stylesheet"
    href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
   	<!-- ヘッダー -->
	<%@ include file="/common/header.jsp"%>
	
	<%
	if (user != null) {
		// セッションをクリア
		request.getRequestDispatcher("/logout").forward(request, response);
	}
	%>

    <main>
        <div class="container">
            <h2>ログイン</h2>
            <form action="<%=request.getContextPath()%>/login" method="post">
                <label for="email">メールアドレス:</label>
                <input type="email" name="email" required>
                <label for="password">パスワード:</label>
                <input type="password" name="password" required>
                <button type="submit">ログイン</button>
            </form>
            <p>アカウントがありませんか？ <a href="<%=request.getContextPath()%>/view/signUp.jsp">新規登録</a>
        </div>
    </main>

   <%--フッター --%>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>