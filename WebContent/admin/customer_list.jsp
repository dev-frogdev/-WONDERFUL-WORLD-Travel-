<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Customers - WOW Travel Administration</title>
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../js/jquery-ui.min.css">
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h1 class="pageheading">Customers Management</h1>
		<h3><a href="customer_form.jsp">Create New Customer</a></h3>
	</div>

	<c:if test="${message !=null}">
		<div align="center">
			<h4 class="message">${message}</h4>
		</div>
	</c:if>

	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>E-mail</th>
				<th>Full Name</th>
				<th>Passport Number</th>
				<th>Country Of Passport</th>
				<th>Registered Date</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="customer" items="${listCustomer}" varStatus="status">
			<tr>
			<td>${status.index + 1}</td>
			<td>${customer.customerId}</td>
			<td>${customer.email}</td>
			<td>${customer.fullname}</td>
			<td>${customer.passportNumber}</td>
			<td>${customer.registerOn}</td>
			<td>${customer.passportCountry}</td>
			<td>
			<a href="edit_customer?id=${customer.customerId}">Edit</a> &nbsp;			
			<a href="javascript:void(0);" class="deleteLink" id="${customer.customerId}">Delete</a>
			</td>
			</tr>			
			</c:forEach>
		</table>
	</div>

	<jsp:directive.include file="footer.jsp" />
	
	<script>
	$(document).ready(function() {
		$(".deleteLink").each(function() {
			$(this).on("click", function() {
				customerId = $(this).attr("id");
				if(confirm('Are you sure you want to delete the customer with ID' + customerId + '?')) {
					window.location = 'delete_customer?id=' + customerId;			
				}
			});
			
		});
	});
	</script>
</body>
</html>