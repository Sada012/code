<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Merchandise,bean.User"%>

<%
//ユーザー情報の取得
User user_list = (User) session.getAttribute("user_list");
// 商品情報の取得
ArrayList<Merchandise> list = (ArrayList<Merchandise>) request.getAttribute("goods_list");
//変数の宣言
int count = 0;
%>


<html>
<head>
<meta charset="UTF-8">
<title>商品一覧</title>

<%--CSS接続 --%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>

	<!-- ヘッダー -->
	<%@ include file="/common/header.jsp"%>
	
	<main>
	<%--テキストボックス--%>
	<form action="<%=request.getContextPath()%>/searchServlet" method="get">
		<input type="text" size="30" placeholder="検索" name="search"></input> 
		<input type="submit" size="30" value="検索"></input>
	</form>


		<div class="container">

			<%--goods_listにデータがある間表示 --%>

			<section class="product-list">
				<%
				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
						Merchandise merchandise = (Merchandise) list.get(i);
				%>

				<div class="product-item">

					<img src="<%=request.getContextPath()%><%=merchandise.getPhoto()%>">
					<h3>
						<p><%=merchandise.getProduct_name()%>

					</h3>
					<p>
						<%=merchandise.getPrice()%>
					</p>

					<%-- 会員情報によるリンク先の条件式 --%>
					<%
					if (user == null) {
					%>
					<a
						href="<%=request.getContextPath()%>/view/login.jsp?merchandise_id=<%=merchandise.getMerchandise_id()%>">詳細の表示</a>
					<%
					} else if (user.getAuthority() == 1) {
					%>
					<a
						href="<%=request.getContextPath()%>/adminGoodsDetail?merchandise_id=<%=merchandise.getMerchandise_id()%>">商品の詳細</a>
					<%
					} else if (user.getAuthority() == 2) {
					%>
					<a
						href="<%=request.getContextPath()%>/goodsDetail?merchandise_id=<%=merchandise.getMerchandise_id()%>">商品の詳細</a>
					<%
					}
					%>
				</div>

				<%
				count++;
				if (count == 3) {
					count = 0;
				%>

				<br>
				<%
				}
				}
				}
				%>

			</section>
		</div>
	</main>

	<%--フッター --%>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>