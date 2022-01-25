<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Tours - WOW Travel Administration</title>
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../js/jquery-ui.min.css">
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h1 class="pageheading">Tours Management</h1>
		<h3>
			<a href="new_tour">Create New Tour</a>
		</h3>
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
				<th>Image</th>
				<th>Title</th>
				<th>Country, City</th>
				<th>Type</th>
				<th>Price</th>
				<th>Last Updated</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="tour" items="${listTours}" varStatus="status">
			<tr>
			<td>${status.index + 1}</td>
			<td>${tour.tourId}</td>
			<td>
			<img src="data:image/jpg;base64,${tour.baseImage}" width="84" height="110" />
			</td>
			
			<td>${tour.title}</td>
			<td>${tour.cityCountry}</td>
			<td>${tour.category.name}</td>
			<td>$${tour.price}</td>
			<td><fmt:formatDate pattern='MM/dd/yyyy' value='${tour.lastUpdatedOn}'/></td>
			<td>
			<a href="edit_tour?id=${tour.tourId}">Edit</a> &nbsp;			
			<a href="javascript:void(0);" class="deleteLink" id="${tour.tourId}">Delete</a>
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
				tourId = $(this).attr("id");
				if(confirm('Are you sure you want to delete the tour with ID' + tourId + '?')) {
					window.location = 'delete_tour?id=' + tourId;			
				}
			});
			
		});
	});
	</script>
</body>
</html>