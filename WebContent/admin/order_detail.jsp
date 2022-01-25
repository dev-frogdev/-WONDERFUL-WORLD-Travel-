<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Details - WOW Travel Administration</title>
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../js/jquery-ui.min.css">
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2 class="pageheading">Details Of Order ID: ${order.orderId}</h2>
	</div>

	<c:if test="${message !=null}">
		<div align="center">
			<h4 class="message">${message}</h4>
		</div>
	</c:if>

	<div align="center">
		<h2>Order Overview:</h2>

		<table border="1" cellpadding="5">
			<tr>
				<td><b>Order by:</b></td>
				<td>${order.customer.fullname}</td>
			</tr>
			<tr>
				<td><b>Quantity Of Tours:</b></td>
				<td>${order.tourCopies}</td>
			</tr>
			<tr>
				<td><b>Total Amount:</b></td>
				<td><fmt:formatNumber value="${order.orderTotal}" type="currency" /></td>
			</tr>
			<tr>
				<td><b>Recipient Name:</b></td>
				<td>${order.fullname}</td>
			</tr>
			<tr>
				<td><b>Recipient Phone:</b></td>
				<td>${order.phoneNumber}</td>
			</tr>
			<tr>
				<td><b>Payment Method:</b></td>
				<td>${order.paymentMethod}</td>
			</tr>
			<tr>
				<td><b>Order Status:</b></td>
				<td>${order.orderStatus}</td>
			</tr>
			<tr>
				<td><b>Order Date:</b></td>
				<td>${order.orderDate}</td>
			</tr>
		</table>
		</div>
		<div align="center">
			<h2>Ordered Tours</h2>
			<table border = "1">
				<tr>
					<th>Index</th>
					<th>Tour title</th>
					<th>City, Country</th>
					<th>Price</th>
					<th>Tour Copies</th>
					<th>Subtotal</th>
				</tr>
			<c:forEach  items="${order.orderDetails}" var="orderDetail" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>
					<img style="vertical-align: middle;" src="data:image/jpg;base64,${orderDetail.tour.baseImage}" width="124" height="93" />
					${orderDetail.tour.title}
					</td>
					<td>${orderDetail.tour.cityCountry}</td>
					<td><fmt:formatNumber value="${orderDetail.tour.price}" type="currency" /></td>
					<td>${orderDetail.quantity}</td>
					<td><fmt:formatNumber value="${orderDetail.subtotal}" type="currency" /></td>
				</tr>
			</c:forEach>
			<tr>
			<td colspan="4" align="right" >
				<b><i>TOTAL:</i></b>
				</td>
				<td><b>${order.tourCopies}</b></td>
				<td><fmt:formatNumber value="${order.orderTotal}" type="currency" /></td>
			</tr>
		</table>
		</div>
		<div align="center">
		<br/>
		<a href="edit_order?id=${order.orderId}">Edit this Order</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0);" class="deleteLink" id="${order.orderId}">Delete this Order</a>
	</div>

	<jsp:directive.include file="footer.jsp" />
	
 	<script>
	$(document).ready(function() {
 		$(".deleteLink").each(function() {
 			$(this).on("click", function() {
				orderId = $(this).attr("id");
 				if(confirm('Are you sure you want to delete the order with ID' + orderId + '?')) {
 					window.location = 'delete_order?id=' + orderId;			
				}
		});
			
 		});
 	});
	</script>
</body>
</html>