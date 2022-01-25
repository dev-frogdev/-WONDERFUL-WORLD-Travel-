<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout - WOW Travel</title>
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
			<div>
				<h2>
					Review Your Order Details <a href="view_cart">Edit</a>
				</h2>
				<table border="1">
					<tr>
						<th>N</th>
						<th colspan="2">Tour</th>
						<th>City, Country</th>
						<th>Price</th>
						<th>Tour Copies</th>
						<th>Subtotal</th>
					</tr>
					<c:forEach items="${cart.items}" var="item" varStatus="status">
						<tr>
							<td>${status.index + 1}</td>
							<td><img class="tour-small" src="data:image/jpg;base64,${item.key.baseImage}" /></td>
							<td><span id="tour-title">${item.key.title}</span></td>
							<td>${item.key.cityCountry}</td>
							<td><fmt:formatNumber value="${item.key.price}" type="currency" /></td>
							<td><input type="text" name="quantity${status.index + 1}" value="${item.value}" size="5" readonly="readonly" /></td>
							<td><fmt:formatNumber value="${item.value * item.key.price}" type="currency" /></td>
						</tr>
					</c:forEach>

					<tr>
						<td></td>
						<td></td>
						<td><b>${cart.totalQuantity} tour(s)</b></td>
						<td></td>
						<td colspan="2"><b><fmt:formatNumber value="${cart.totalAmount}" type="currency" /></b></td>
						<td></td>
					</tr>
				</table>
				<h2>Your Information For Sending The Order</h2>
				<form id="orderForm" action="place_order" method="post">
					<table>
						<tr>
							<td>Recipient Name:</td>
							<td><input type="text" name="fullname" value="${loggedCustomer.fullname}" /></td>
						</tr>
						<tr>
							<td>Recipient Phone:</td>
							<td><input type="text" name="phoneNumber" value="${loggedCustomer.phoneNumber}" /></td>
						</tr>
						<tr>
							<td>Date Of Birth:</td>
							<td><input type="text" name="dateOfBirth" value="${loggedCustomer.dateOfBirth}" /></td>
						</tr>
						<tr>
							<td>Passport Number:</td>
							<td><input type="text" name="passportNumber" value="${loggedCustomer.passportNumber}" /></td>
						</tr>
						<tr>
							<td>Passport Country:</td>
							<td><input type="text" name="passportCountry" value="${loggedCustomer.passportCountry}" /></td>
						</tr>
						<tr>
							<td>Passport Date:</td>
							<td><input type="text" name="passportDate" value="${loggedCustomer.passportDate}" /></td>
						</tr>
						<tr>
							<td>Passport Authority:</td>
							<td><input type="text" name="passportAuthority" value="${loggedCustomer.passportAuthority}" /></td>
						</tr>
					</table>
					<div>
						<h2>Payment</h2>
						Choose your payment method: &nbsp;&nbsp;&nbsp; 
						<select name="paymentMethod">
							<option value="Card Payment">Card Payment</option>
						</select>
					</div>
					<div>
						<table class="normal">
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td><button type="submit">Place Order</button></td>
								<td><h3><a href="${pageContext.request.contextPath}/">Continue Shopping</a></h3></td>
								</tr>
						</table>
					</div>
				</form>
			</div>
		</c:if>

	</div>
	<jsp:directive.include file="footer.jsp" />
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#orderForm").validate({
		
				rules: {
					fullname: "required", 
					phoneNumber: "required",
					dateOfBirth: "required",
					passportNumber: "required",
					passportDate: "required",
					passportCountry: "required",
					passportAuthority: "required",
					},
					
					messages: {
						fullname: "Please enter full name", 
						phoneNumber: "Please enter phone number",
						dateOfBirth: "Please enter date of birth",
						passportNumber: "Please enter number of passport",
						passportDate: "Please enter passport date of issue / date of expiry",
						passportCountry: "Please enter your passport country",
						passportAuthority: "Please enter place/authority of issue the passport",	
					}
					});
			});
	</script>
</body>
</html>