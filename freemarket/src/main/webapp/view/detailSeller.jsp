<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="bean.User,bean.Purchase,bean.Merchandise,java.util.ArrayList"%>

<%
//リクエストスコープより、seller、seller_list、purchaseを取り出す
User seller = (User) request.getAttribute("seller");
ArrayList<Merchandise> merchandise = (ArrayList<Merchandise>) request.getAttribute("seller_list");
String purchase_day = (String) request.getAttribute("purchase");
if(purchase_day == null){
	 purchase_day = "販売中";
}
%>

<html>
<head>

	<title>詳細機能を実装する</title>
	<%--CSS接続 --%>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">

</head>
<body>
<!-- ヘッダー -->
	<%@ include file="/common/header.jsp"%>
	<main>
	<div class="container">
	<table class="table_style">
		<tr>
			<td>
				ID：<%=seller.getUser_id() %>
			</td>
			<td>
				名前：<%=seller.getName() %>
			</td>
			<td>
				出品数：<%=merchandise.size() %>
			</td>
		</tr>
	
	</table>

	<%--詳細画面--%>
	<div class="container">
		<table class="table_style">
		
			<tr>
				<td>商品ID</td>
				<td>商品名</td>
				<td>価格</td>
				<td>取引状況</td>
				<td>売上</td>
				<td>出品日</td>
				<td>売上日</td>
			</tr>
			<%
			//売上、合計売上の初期化
			int total = 0;
			int earnings = 0;
			
			for(int i = 0 ; i < merchandise.size() ; i++){
				Merchandise merchandises = (Merchandise) merchandise.get(i) ;

			%>
			
			<tr>
				<td class="center">
					<%=merchandises.getMerchandise_id() %>
				</td>
				
				<td class="center">
					<%=merchandises.getProduct_name() %>
				</td>
				
				<td class="center">
					<%=merchandises.getPrice() %>円
				</td>
				<td class="center">
					<%=merchandises.getSales_status() %>
				</td>
				
				<%
				//もし入金されていた場合
				if(merchandises.getSales_status() == 2){
					//売上に入金分を格納
					earnings = merchandises.getPrice();
					//合計売上額を更新
					total += earnings;
					
				}
				
				%>
				<td class="center">
					<%=earnings %>円
				</td>
				<td class="center">
					<%=merchandises.getListing_date() %>
				</td>
				<td class="center">
					<%= purchase_day %>
				</td>
				
			</tr>
			
			<%
			}
			%>
			
		</table>
		
		<div>
		合計売上：<%=total %>円
		
		</div>
		</div>
		</div>
	</main>
	<%--フッター --%>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>