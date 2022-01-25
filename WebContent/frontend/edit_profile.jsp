<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit a Customer</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="js/jquery-ui.min.css">
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
<jsp:directive.include file="header.jsp" />
	<div align="center">
		<h2 class="pageheading">
		Edit My Profile
	</h2>
	</div>
<div align="center">
			<form action="update_profile" method="post" id="customerForm"" >
		
		<table class="form">
			<tr>
				<td align="right">E-mail:</td>
				<td align="left"><b>${loggedCustomer.email}</b> (Cannot be changed)</td>
			</tr>
			<tr>
				<td align="right">Full Name:</td>
				<td align="left"><input type="text" id="fullname"
					name="fullname" size="50" value="${loggedCustomer.fullname}" /></td>
			</tr>
			<tr>
				<td align="right">Phone Number:</td>
				<td align="left"><input type="text" id="phoneNumber"
					name="phoneNumber" size="50" value="${loggedCustomer.phoneNumber}" /></td>
			</tr>
			<tr>
				<td align="right">Date Of Birth:</td>
				<td align="left"><input type="text" id="dateOfBirth"
					name="dateOfBirth" size="50" value="${loggedCustomer.dateOfBirth}" /></td>
			</tr>
			<tr>
				<td align="right">Passport Number:</td>
				<td align="left"><input type="text" id="passportNumber"
					name="passportNumber" size="50" value="${loggedCustomer.passportNumber}" /></td>
			</tr>
			<tr>
				<td align="right">Date Of Issue/Date Of Expiry:</td>
				<td align="left"><input type="text" id="passportDate"
					name="passportDate" size="50" value="${loggedCustomer.passportDate}" /></td>
			</tr>
			<tr>
				<td align="right">Country Of Passport:</td>
				<td align="left"><input type="text" id="passportCountry"
					name="passportCountry" size="50" value="${loggedCustomer.passportCountry}" /></td>
			</tr>
			<tr>
				<td align="right">Place/Authority Of Issue:</td>
				<td align="left"><input type="text" id="passportAuthority"
					name="passportAuthority" size="50" value="${loggedCustomer.passportAuthority}" /></td>
			</tr>
				<tr>
					<td colspan="2" align="center">
					<i>(Leave password fields blank if you don't want to change password)</i>
					</td>
				</tr>
				<tr>
				<td align="right">Password:</td>
				<td align="left"><input type="password" id="password"
					name="password" size="50" /></td>
			</tr>
			<tr>
				<td align="right">Confirm Password:</td>
				<td align="left"><input type="password" id="confirmPassword"
					name="confirmPassword" size="50" /></td>
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
		
		confirmPassword: {
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
					
					confirmPassword: {	
					equalTo: "Confirm password does not match password"
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