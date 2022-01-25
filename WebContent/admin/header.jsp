<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.css"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
</head>
<body>
	<div align="center">
		<div>
		<a href="${pageContext.request.contextPath}/admin/">
			<img src="../images/WOWTravelAdmin.png" />
		</a>
		</div>
		<div>
		<br/>
			Welcome, <c:out value="${sessionScope.useremail}" /> | <a href="logout">Logout</a>
		<br/><br/>
		</div>
		<div id="headermenu">
			<div class="menu_item" style="display:table-cell; padding-right: 27px;">
				<a href="list_users"> 
				<img src="../images/users.png" /><br />Users
				</a>
			</div>
			<div class="menu_item" style="display:table-cell; padding-right: 17px;">	
				<a href="list_category"> 
				<img src="../images/category.png" /><br />Types 
				</a> 
			</div>
			<div class="menu_item" style="display:table-cell; padding-right: 27px;">
				<a href="list_tours">
				<img src="../images/tours.png" /><br />Tours 
				</a> 
			</div>
			<div class="menu_item" style="display:table-cell; padding-right: 34px;">
				<a href="list_customer"> 
				<img src="../images/customer.png" /><br />Customers
				</a>
			</div> 
			<div class="menu_item" style="display:table-cell; padding-right: 25px;">
				<a href="list_review"> 
				<img src="../images/review.png" /><br />Reviews
				</a>
			</div> 
			<div class="menu_item" style="display:table-cell; padding-right: 14px;">
				<a href="list_order"><img src="../images/order.png" /><br />
				Orders
				</a>
			</div>
		</div>
	</div>
</body>
</html>