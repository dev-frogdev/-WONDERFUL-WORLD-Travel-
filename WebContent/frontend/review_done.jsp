<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Review Posted - WOW Travel</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
</head>
<body>
<jsp:directive.include file="header.jsp" />

	<div align="center">
	<form id="reviewForm" action="submit_review" method="post" >
	<table class="normal" width="70%">
	<tr>
	<td><h2>Your Reviews</h2></td>
	<td>&nbsp;</td>
	<td><h2>${loggedCustomer.fullname}</h2></td>
	</tr>
	<tr>
	<td colspan="3"><hr/></td>
	</tr>
	<tr>
	<td>
	<span id="tour-title">${tour.title}</span><br/>
	<img class="tour-large" src="data:image/jpg;base64,${tour.baseImage}" />
	</td>
	<td colspan="2">
	<h3>You review has been posted. Thank you!</h3>
	</td>
	</tr>
	</table>	
	</form>
	</div>
	
	<jsp:directive.include file="footer.jsp" />
</body>
</html>