<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WOW Travel Administration</title>
<link rel="stylesheet" href="../css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2 class="pageheading">Administrative Dashboard</h2>
	</div>

	<div align="center">
	<hr width="50%"/>
		<h2 class="pageheading">Quick Actions:</h2>
		<b> 
		<a href="user_form.jsp">New User</a> &nbsp; 
		<a href="category_form.jsp">New Type</a> &nbsp; 
		<a href="new_tour">New Tour</a> &nbsp; 
		<a href="customer_form.jsp">New Customer</a> &nbsp;
		</b>
	</div>

	<div align="center">
		<hr />
		<h2 class="pageheading">Recent Sales:</h2>
		<table>
		<tr>
		<th>Order ID</th>
		<th>Ordered By</th>
		<th>Quantity Of Tours</th>
		<th>Total Amount</th>
		<th>Payment Method</th>
		<th>Status</th>
		<th>Order Date</th>
		</tr>
		<c:forEach items="${listMostRecentSales}" var="order" varStatus="status">
		<tr>
		<td><a href="view_order?id=${order.orderId}">${order.orderId}</a></td>
		<td>${order.customer.fullname}</td>
		<td>${order.tourCopies}</td>
		<td><fmt:formatNumber value="${order.orderTotal}" type="currency" /></td>
		<td>${order.paymentMethod}</td>
		<td>${order.orderStatus}</td>
		<td>${order.orderDate}</td>
		</tr>
		</c:forEach>
		</table>
	</div>

	<div align="center">
		<hr />
		<h2 class="pageheading">Recent Reviews:</h2>
	</div>

	<div align="center">
		<hr />
		<h2 class="pageheading">Statistics:</h2>
		<hr />
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>
</html>