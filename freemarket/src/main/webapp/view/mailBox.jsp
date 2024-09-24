<%@page contentType="text/html; charset=UTF-8"%>


<html>
<head>
<meta charset="UTF-8">
<title>メールボックス - フリマアプリ</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>


	<main>
		<section id="mailbox">
			<h1>メールボックス</h1>


			<%-- <
				if(mail != null){
					for(int i=0;i<mail.size();i++){
					Mail mailbox = (Mail)mail.get(i);
					% --%>

			<div class="mailbox">
				<div class="mail-item">
					<input type="checkbox"> <span class="sender"><a
						href="<%=request.getContextPath()%>/messageConsemer>">送信者1</a></span> <span
						class="subject">件名1</span> <span class="date">2024-06-25</span>
				</div>

				<div class="mail-item">
					<input type="checkbox"> <span class="sender"><a
						href="<%=request.getContextPath()%>/messageConsemer>">送信者1</a></span></span> <span
						class="subject">件名2</span> <span class="date">2024-06-24</span>
				</div>

				<%-- 削除ボタン --%>
				<td><form action="<%=request.getContextPath()%>/delete">
						<input type="hidden" name="isbn" value="<%--book.getIsbn()--%>">
						<input type="submit" value="削除"></td>
			</div>

			<%--
            }
            }
             --%>
		</section>
	</main>

	<script src="mailbox.js"></script>
</body>
</html>