<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Order History - WOW Travel</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="js/jquery-ui.min.css">
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<div align="center">
	<h2 class="pageheading">My Order History</h2>
	</div>
	
	<c:if test="${fn:length(listOrders) == 0}">
	<div align="center">
	<h3>You have not place any orders.</h3>
	</div>
	</c:if>

	<c:if test="${fn:length(listOrders) > 0}">
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>Order ID</th>
				<th>Quantity Of Tours</th>
				<th>Total Amount</th>
				<th>Payment Method</th>
				<th>Order Date</th>
				<th>Status</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="order" items="${listOrders}" varStatus="status">
			<tr>
			<td>${status.index + 1}</td>
			<td>${order.orderId}</td>
			<td>${order.tourCopies}</td>
			<td><fmt:formatNumber value="${order.orderTotal}" type="currency" /></td>
			<td>${order.paymentMethod}</td>
			<td>${order.orderDate}</td>
			<td>${order.orderStatus}</td>
			<td>
			<a href="show_order_detail?id=${order.orderId}">View Details</a> &nbsp;
			</td>
			</tr>			
			</c:forEach>
		</table>
	</div>
	</c:if>

	<jsp:directive.include file="footer.jsp" />
	
</body>
</html>