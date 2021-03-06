<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--静态包含base标签、Css样式、JQuery文件--%>
	<%@ include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function (){
			$("a.deleteItem").click(function (){
				return confirm("你确定要删除《" + $(this).parent().parent().find("td:first").text() + "》吗？");
			});
			$("a.clearCart").click(function (){
				return confirm("你确定要删除清空购物车吗？");
			});
			$("input.updateItem").change(function (){
				var name = $(this).parent().parent().find("td:first").text();
				var count =  this.value;
				if(confirm("你确定要将《" + name + "》数量修改为" + count + "吗？")){
					location.href = "http://localhost:8080/DynamicBookStore/cartServlet?action=updateItemCount&id="
							+ $(this).attr("itemId") + "&count=" + count;
				}else {
					this.value = this.defaultValue;
				}
			});
		});
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%@ include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items.values()}" var="item">
					<tr>
						<td>${item.name}</td>
						<td>
							<input class="updateItem" type="text" value="${item.count}" itemId="${item.id}" style="width: 50px;height: 20px;text-align: center">
						</td>
						<td>${item.price}</td>
						<td>${item.totalPrice}</td>
						<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${item.id}">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty sessionScope.cart.items}">
					<tr>
						<td colspan="5"><a href="index.jsp">当前购物车为空，点击此处浏览商品！</a></td>
					</tr>
			</c:if>

			
		</table>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.price}</span>元</span>
				<span class="cart_span"><a class="clearCart" href="cartServlet?action=clearCart">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>
	
	</div>

	<%--静态引入页脚--%>
	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>