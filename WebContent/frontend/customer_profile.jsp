<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Profile - WOW Travel</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="js/jquery-ui.min.css">
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>

	<jsp:directive.include file="header.jsp" />


	<div align="center">
		<br />
		<h2>Welcome, ${loggedCustomer.fullname}</h2>
		<br />
		<table class="normal" border="1" cellpadding="5">
			<tr>
				<td><b>E-mail:</b></td>
				<td>${loggedCustomer.email}</td>
			</tr>
			<tr>
				<td><b>Full Name:</b></td>
				<td>${loggedCustomer.fullname}</td>
			</tr>
			<tr>
				<td><b>Phone Number:</b></td>
				<td>${loggedCustomer.phoneNumber}</td>
			</tr>
			<tr>
				<td><b>Date Of Birth:</b></td>
				<td>${loggedCustomer.dateOfBirth}</td>
			</tr>
			<tr>
				<td><b>Passport Number:</b></td>
				<td>${loggedCustomer.passportNumber}</td>
			</tr>
			<tr>
				<td><b>Date Of Issue/Date Of Expiry:</b></td>
				<td>${loggedCustomer.passportDate}</td>
			</tr>
			<tr>
				<td><b>Country Of Passport:</b></td>
				<td>${loggedCustomer.passportCountry}</td>
			</tr>
			<tr>
				<td><b>Place/Authority Of Issue:</b></td>
				<td>${loggedCustomer.passportAuthority}</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
			<td colspan="2" align="center"><a href="edit_profile"><b>Edit My Profile</b></a></td>
			</tr>
		</table>
	</div>

	<jsp:directive.include file="footer.jsp" />

</body>
</html>