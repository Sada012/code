<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>メール詳細</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>

	<h1>フリマアプリ</h1>

	<main>
		<div class="container">


			<%--送信者、件名 --%>
			<h3 class="sender">送信者：ここに送信者が表示されるよ</h3>
			<h3 class="subject">件名1：ここに件名が表示されるよ</h3>
			
			<%-- 受け取ったメール --%>
			<h2>ここに受け取ったメールを表示するよこれは後で消してね</h2>


			<table>
				<tr>
					<td>
						<div class="product-item">
							<img
								src="<%=request.getContextPath()%>/img/a.jpg<%--merchandise.getPhoto()--%>">
						</div>
					</td>
					<td>
						<h2>
							商品名:
							<%---merchandise.getProduct_name()--%>
							人形
						</h2>
						<h2>
							価格:
							<%--merchandise.getPrice()--%>
							￥200
						</h2>
					</td>
				</tr>
			</table>


			<%--ここからしたは吉野さんからもらったコード --%>
			
			
			<form action="<%=request.getContextPath()%>/chat" method="post">
			<label for="recipient">宛先:</label>
                <input type="text" id="recipient" name="recipient" value="<%-- user_recipient.getName() --%>" required>
                <label for="recipient">件名:</label>
				<input type="text" name="sender" placeholder="Your Name" required>
				<label for="recipient">本文:</label>
				<textarea name="content" placeholder="Message..." required></textarea>
				<input type="submit" value="送信">
			</form>