<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register as a Customer</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2 class="pageheading">
		Register as a Customer
	</h2>
	</div>

<div align="center">
			<form action="register_customer" method="post" id="customerForm"" >
		
		<table class="form">
			<tr>
				<td align="right">E-mail:</td>
				<td align="left"><input type="text" id="email"
					name="email" size="50" /></td>
			</tr>
			<tr>
				<td align="right">Full Name:</td>
				<td align="left"><input type="text" id="fullname"
					name="fullname" size="50" /></td>
			</tr>
			<tr>
				<td align="right">Password:</td>
				<td align="left"><input type="text" id="password"
					name="password" size="50" /></td>
			</tr>
			<tr>
				<td align="right">Confirm Password:</td>
				<td align="left"><input type="password" id="confirmPassword"
					name="confirmPassword" size="50" /></td>
			</tr>
			<tr>
				<td align="right">Phone Number:</td>
				<td align="left"><input type="text" id="phoneNumber"
					name="phoneNumber" size="50" /></td>
			</tr>
			<tr>
				<td align="right">Date Of Birth:</td>
				<td align="left"><input type="text" id="dateOfBirth"
					name="dateOfBirth" size="50" /></td>
			</tr>
			<tr>
				<td align="right">Passport Number:</td>
				<td align="left"><input type="text" id="passportNumber"
					name="passportNumber" size="50" /></td>
			</tr>
			<tr>
				<td align="right">Date Of Issue/Date Of Expiry:</td>
				<td align="left"><input type="text" id="passportDate"
					name="passportDate" size="50" /></td>
			</tr>
			<tr>
				<td align="right">Country Of Passport:</td>
				<td align="left"><input type="text" id="passportCountry"
					name="passportCountry" size="50" /></td>
			</tr>
			<tr>
				<td align="right">Place/Authority Of Issue:</td>
				<td align="left"><input type="text" id="passportAuthority"
					name="passportAuthority" size="50" /></td>
			</tr>	
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<button type="submit">Save</button>&nbsp;&nbsp;&nbsp; 
				<button id="buttonCancel">Cancel</button>
				</td>
			</tr>
		</table>
		</form>
	</div>

	<jsp:directive.include file="footer.jsp" />
	</body>
	<script type="text/javascript">
	
	$(document).ready(function() {
	$("#customerForm").validate({
			rules: {
		email: {
			required: true,
			email: true
		},
		fullname: "required",
		password: "required",	
		confirmPassword: {
			required: true,
			equalTo: "#password"
		},
		phoneNumber: "required",
		dateOfBirth: "required",	
		passportNumber: "required",
		passportDate: "required",
		passportCountry: "required",
		passportAuthority: "required",
		
		},
			
			messages: {
					email: {
					required: "Please enter e-mail address",
					email: "Please enter a valid e-mail address"
					},
					
					fullname: "Please enter full name",
					password: "Please enter password",	
					confirmPassword: {
						required: "Please confirm password",
					equalTo: "Confirm rassword password does not match password"
					},
					phoneNumber: "Please enter phone number",
					dateOfBirth: "Please enter date of birth",	
					passportNumber: "Please enter passport number",
					passportDate: "Please enter passport date",
					passportCountry: "Please enter passport country",
					passportAuthority: "Please enter passport authority",
			}
		});

		$("#buttonCancel").click(function() {
			history.go(-1);
		});
	});	
	
</script>
</html>