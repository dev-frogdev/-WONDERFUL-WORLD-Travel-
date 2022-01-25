<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>Edit Order - WOW Travel Administration</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2 class="pageheading">Edit Order ID: ${order.orderId}</h2>
	</div>

	<c:if test="${message !=null}">
		<div align="center">
			<h4 class="message">${message}</h4>
		</div>
	</c:if>
	
	<form action="update_order" method="post" id="orderForm">
	<div align="center">
	
		<table border = "1" class="normal" border="1" cellpadding="5">
		<tr>
				<td><b>Order by: </b></td>
				<td>${order.customer.fullname}</td>
			</tr>
			<tr>
				<td><b>Order Date: </b></td>
				<td>${order.orderDate}</td>
			</tr>
			<tr>
				<td><b>Recipient Name: </b></td>
				<td><input type="text" name="recipientName" value="${order.fullname}" size="45" /></td>
			</tr>
			<tr>
				<td><b>Recipient Phone: </b></td>
				<td><input type="text" name="recipientPhone" value="${order.phoneNumber}" size="45" /></td>
			</tr>
			<tr>
				<td><b>Send To: </b></td>
				<td><input type="text" name="sendTo" value="${order.sendTo}" size="45" /></td>
			</tr>
			<tr>
				<td><b>Payment Method: </b></td>
				<td>
				<select name="paymentMethod">
				<option value="Card Payment">Card Payment</option>
				</select>
				</td>
			</tr>
			<tr>
				<td><b>Order Status: </b></td>
				<td>
				<select name="orderStatus">
				<option value="Processing" <c:if test="${order.orderStatus eq 'Processing' }">selected='selected'</c:if> > Processing</option>
				<option value="Completed" <c:if test="${order.orderStatus eq 'Completed' }">selected='selected'</c:if> >Completed</option>
				<option value="Cancelled" <c:if test="${order.orderStatus eq 'Cancelled' }">selected='selected'</c:if> >Cancelled</option>
				</select>
				</td>
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
					<th>Quantity</th>
					<th>Subtotal</th>
					<th><a href="">Add Tours</a></th>
				</tr>
			<c:forEach items="${order.orderDetails}" var="orderDetail" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${orderDetail.tour.title}</td>
					<td>${orderDetail.tour.cityCountry}</td>
					<input type="hidden" name="price" value="${orderDetail.tour.price}" />
					<td><fmt:formatNumber value="${orderDetail.tour.price}" type="currency" /></td>
					<td>
					<input type="hidden" name="tourId" value="${orderDetail.tour.tourId}" />
					<input type="text" name="quantity${status.index + 1}" value="${orderDetail.quantity}" size="5" />
					</td>
					<td><fmt:formatNumber value="${orderDetail.subtotal}" type="currency" /></td>
					<td><a href="remove_tour_from_order?id=${orderDetail.tour.tourId}">Remove</a></td>
				</tr>
			</c:forEach>
			<tr>
			<td colspan="4" align="right" >
				<b><i>TOTAL:</i></b>
				</td>
				<td><b>${order.tourCopies}</b></td>
				<td><fmt:formatNumber value="${order.orderTotal}" type="currency" /></td>
		<td></td>
		</tr>
		</table>
		</div>
		<div align="center">
		<br/>
		<a href="javascript:showAddTourPopup()"><b>Add Tours</b></a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="submit" value="Save" />
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value="Cancel" onclick="javascript:window.location.href='list_order';" />
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</div>
	</form>
	<jsp:directive.include file="footer.jsp" />
	<script>
	function showAddTourPopup() {
		var width = 600;
		var heigth = 250;
		var left = (screen.width - width) / 2;
		var top = (screen.heigth - heigth) / 2;
		window.open('add_tour_form', '_blank', 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left);
}

	$(document).ready(function() {
		$("#orderForm").validate({
			rules: {		
		fullname: "required",
		phoneNumber: "required",
		sendTo: "required",
			
		<c:forEach items="${order.orderDetails}" var="tour" varStatus="status">
			quantity${status.index + 1} : { 
			required: true, number: true, min: 1 
			},
		</c:forEach>
			},
			
			messages: {
				fullname: "Please enter full name",
				phoneNumber: "Please enter phone number",
				sendTo: "Please enter the information to send",
				
					<c:forEach items="${order.orderDetails}" var="tour" varStatus="status">
				quantity${status.index + 1} : { 
				required: "Please enter quantity", 
				number: "Quantity must be a number",
				min: "Quantity must be greater than 0"
				},
				</c:forEach>
			}
		});
		
	});	
	
	</script>
</body>
</html>