<%@page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新規登録</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
   	<!-- ヘッダー -->
	<%@ include file="/common/header.jsp"%>
	
	<main>
        <div class="container">
            <h2>新規登録</h2>
		<form action="<%=request.getContextPath() %>/signUp"
			enctype="multipart/form-data" method="post">
			<label for="name">本名:</label>
            <input type="text" id="name" name="name" required>
            <label for="nickname">ニックネーム:</label>
            <input type="text" id="nickname" name="nickname" required>
            <label for="password">パスワード:</label>
            <input type="password" id="password" name="password" required>
            <label for="password_sub">パスワード(確認用):</label>
            <input type="password" id="password_sub" name="password_sub" required>
			<label for="email">メールアドレス:</label>
            <input type="email" id="email" name="email" required>
            <label for="post_code">郵便番号:</label>
            <input type="text" id="post_code" name="post_code" required>
            <label for="address">住所:</label>
            <input type="text" id="address" name="address" required>
            <label for="icon">アイコン:</label>
            <input type="file" name="icon" class="input-file" accept="image/*" >
            <input type="hidden" id="authority" name="authority" value="2">
			<button type="submit">登録</button>
		</form>
		</div>
    </main>
		
	<%--フッター --%>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>
</body>
</html>