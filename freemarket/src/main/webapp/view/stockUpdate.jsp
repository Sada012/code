<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList,bean.User,bean.Merchandise,dao.MerchandiseDAO"%>
<%
String merchandise_id = request.getParameter("merchandise_id");
MerchandiseDAO merchandiseObjDao = new MerchandiseDAO();
Merchandise merchandise = merchandiseObjDao.selectByMerchandise_id(merchandise_id);
PurchaseDAO purchaseObjDao = new PurchaseDAO();
Purchase purchase = purchaseObjDao.selecByPurchase(merchandise_id);
%>
<html>
<head>
<meta charset="UTF-8">
<title>商品情報更新</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>

<body>

	<!-- ヘッダー -->
	<%@ include file="/common/header.jsp"%>
	<main>
		<div class="container">
			<h2>商品情報更新</h2>
			<form action="<%=request.getContextPath()%>/stockUpdate"
				enctype="multipart/form-data" method="post">
				<input type="file" name="photo" class="input-file" accept="image/*" >
				<%--コンテキストパスを後で変える --%>
				<label for="product_name">商品名:</label> 
				<input type="text" name="product_name" value="<%=merchandise.getProduct_name()%>" required> 
				<label for="price">価格:</label> 
				<input type="text" name="price" value="<%=merchandise.getPrice()%>" required>
				<label for="kinds">種類:</label>
				<input type="text" name="kinds" value="<%=merchandise.getKinds()%>" required>
				<label for="remarks">備考:</label> 
				<input type="text" name="remarks" value="<%=merchandise.getRemarks()%>" required>
				<%
				if(purchase.getDeposit_status() == 1){
				%>
				<select name="trading_status">
   					 <option value="1">発送済み</option>
  					  <option value="2" selected>未発送</option>
  					  <option value="3">キャンセル</option>
					  <option value="4">返品済み</option>
					  <option value="5">保留中</option>
				</select>
				<%
				} else {
				%>
				<select name="trading_status">
						<option value="2" selected>入金されていません</option>
				</select>
				<%
				}
				%>
				
				<input type="hidden" name="sales_status" value="<%=merchandise.getSales_status()%>" required>
				<input type="hidden" name="merchandise_id" value="<%=merchandise.getMerchandise_id()%>" required>

				<button type="submit">変更</button>
			</form>
		</div>
	</main>
	<%--フッター --%>
	<%@ include file="/common/footer.jsp"%>
	
	<script>
    document.getElementById('sales_status').addEventListener('change', function() {
        var selectedValue = this.value;
        console.log('選択された値: ' + selectedValue);
    });
</script>
</body>
</html>