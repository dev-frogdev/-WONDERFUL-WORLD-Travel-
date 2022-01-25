<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Shopping Cart</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="js/jquery-ui.min.css">
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
<jsp:directive.include file="header.jsp" />

	<div align="center">
	<tr><td>&nbsp;</td></tr>
	<h2>Your Shopping Cart Details</h2>
		
	<c:if test="${message !=null}">
		<div align="center">
			<h4 class="message">${message}</h4>
		</div>
	</c:if>
	
	<c:set var="cart" value="${sessionScope['cart']}" />
	
	<c:if test="${cart.totalItems == 0}">
	<h2>There are no items in your cart</h2>
	</c:if>

		<c:if test="${cart.totalItems > 0}">
			<form action="update_cart" method="post" id="cartForm">
				<div>
					<table border="1">
						<tr>
							<th>N</th>
							<th colspan="2">Tour</th>
							<th>Tour Copies</th>
							<th>Price</th>
							<th>Subtotal</th>
							<th>
							<a href=""><b>Clear Cart</b></a>
							</th>
						</tr>
					<c:forEach items="${cart.items}" var="item" varStatus="status">
							<tr>
								<td>${status.index + 1}</td>
								<td>
								<img class="tour-small" src="data:image/jpg;base64,${item.key.baseImage}" />
								</td>
								<td>
								<span id="tour-title">${item.key.title}</span>
								</td>
								<td>
								<input type="hidden" name="tourId" value="${item.key.tourId}" />
								<input type="text" name="quantity${status.index + 1}" value="${item.value}" size="5" />
								</td>
								<td><fmt:formatNumber value="${item.key.price}" type="currency" /></td>
								<td><fmt:formatNumber value="${item.value * item.key.price}" type="currency" /></td>
								<td><a href="remove_from_cart?tour_id=${item.key.tourId}">Remove</a></td>
							</tr>
						</c:forEach>
					
					<tr>
					<td></td>
					<td></td>
					<td></td>
					<td><b>${cart.totalQuantity} tour(s)</b></td>
					<td><b>Total</b></td>
					<td colspan="2"><b><fmt:formatNumber value="${cart.totalAmount}" type="currency" /></b></td>
					</tr>
					</table>
					</div>
					<div>
					<table class="normal">
					<tr><td>&nbsp;</td></tr>
					<tr>
					<td></td>
					<td><button type="submit">Update</button></td>
					<td><input type="button" id="clearCart" value="Clear Cart"/></td>
					<td><h3><a href="${pageContext.request.contextPath}/">Continue shopping</a></h3></td>
					<td><h3><a href="checkout">Checkout</a></h3></td>
					</tr>
					</table>
					</div>
				</form>
			
		</c:if>

	</div>
	
	<jsp:directive.include file="footer.jsp" />
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#clearCart").click(function() {
				window.location = 'clear_cart';
			});
			$("#cartForm").validate({
				rules: {
					<c:forEach items="${cart.items}" var="item" varStatus="status">
					quantity${status.index + 1}: { 
						required: true, number: true, min: 1 
						},
				</c:forEach>
			},

				messages: {
					<c:forEach items="${cart.items}" var="item" varStatus="status">
					quantity${status.index + 1}: { 
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