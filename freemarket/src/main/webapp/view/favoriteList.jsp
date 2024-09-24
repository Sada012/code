<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.Merchandise,bean.Favorite,dao.MerchandiseDAO"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<title>お気に入り一覧 - フリマアプリ</title>
</head>

<body>
	<!-- ヘッダー -->
	<%@ include file="/common/header.jsp"%>

	<%--テキストボックス--%>
	<main>
		<div class="container">
	<form action="<%=request.getContextPath()%>/SearchServlet"
			method="get">

			<input type="text" size="30" name="search"></input> <input
				type="submit" size="30" value="検索"></input> <input type="hidden"
				value="1" name="favorite">
	</form>
	<button onclick="">
		<select name="sort">
			<option value="ascending">昇順</option>
			<option value="descending">降順</option>
		</select>
	</button>
	<%
		ArrayList<Favorite> list = (ArrayList<Favorite>) request.getAttribute("list");
		ArrayList<Merchandise> merchandiseList = (ArrayList<Merchandise>) request.getAttribute("merchandiseList");
		
		int count = 0;
		
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
		%>
	<main>
		<div class="container">
			<table>
				<%			
				if(count == 0){					
		%>
				<tr>
					<%
				}
				%>
					<td><img
						src="<%=request.getContextPath()%><%=merchandiseList.get(i).getPhoto()%>"
						width="100" height="100"> <a
						href="<%=request.getContextPath()%>/stockDetail?photo=<%=merchandiseList.get(i).getPhoto()%>">
					</a> <br> <%=merchandiseList.get(i).getPrice() %></td>
					<%
				++count;		
				if (count == 3) {				
		%>
				</tr>
				<%
					count = 0;
				}				
		%>
			</table>
		</div>
	</main>
	<%				
			}
		}		
		%>
		</div>
	</main>
	<%--フッター --%>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>