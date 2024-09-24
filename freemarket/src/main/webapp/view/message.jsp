<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.User"%>

<%
//ユーザー情報の取得
User user_sender = (User) session.getAttribute("user");
User user_recipient = (User) request.getAttribute("user");

%>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>メッセージ - フリマアプリ</title>
    <link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
    <header>
        <div class="container">
            <h1>フリマアプリ</h1>
        </div>
    </header>

    <main>
        <div class="container">
            <h2>メッセージ</h2>
            <form action="<%=request.getContextPath()%>/message" method="post">
            	<input type="hidden" name="sender" value="<%= user_sender.getName() %>" required>
                <label for="recipient">宛先:</label>
                <input type="text" id="recipient" name="recipient" value="<%= user_recipient.getName() %>" required>
                <label for="message">メッセージ内容:</label>
                <textarea id="message" name="message" required></textarea>
                <button type="submit">送信</button>
            </form>
        </div>
    </main>

    <footer>
        <div class="container">
            <p>&copy; 2024 フリマアプリ</p>
        </div>
    </footer>
</body>
</html>
