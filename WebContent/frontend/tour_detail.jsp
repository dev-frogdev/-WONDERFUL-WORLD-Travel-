<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${tour.title} - WOW Travel</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="js/jquery-ui.min.css">
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<table class="tour">
			<tr>
				<td colspan="3" align="left">
					<p id="tour-title">${tour.title}</p> 
					by <span id="author">${tour.cityCountry}</span>
				</td>
			</tr>
			<tr>
				<td rowspan="2">
					<img class="tour-large" src="data:image/jpg;base64,${tour.baseImage}" />
				</td>
				<td valign="top" align="left">
				<jsp:directive.include file="tour_rating.jsp" />&nbsp;&nbsp;
				<a href="#reviews">${fn:length(tour.reviews)} Reviews</a>
				</td>
				<td valign="top" rowspan="2" wigth="20%">
				<h2>${tour.price} грн.</h2>
				<br/>
				<button id="buttonAddToCart">Add to Cart</button>
				</td>
			</tr>
			<tr>
			<td id="depiction">
			${tour.depiction}
			</td>
		</tr>
		<tr>
		<td><h2><a id="reviews">Customer Reviews</a></h2></td>
			<td colspan="2" align="center">
			<button id="buttonWriteReview">Write a Customer Review</button>
			</td>
		</tr>
		<tr>
		<td colspan="3" align="left">
		<table class="normal">
		<c:forEach items="${tour.reviews}" var="review">
		<tr>
		<td>
		<c:forTokens items="${review.stars}" delims="," var="star">
		<c:if test="${star eq 'on'}">
		<img src="images/rating_on.png" />
		</c:if>
		<c:if test="${star eq 'off'}">
		<img src="images/rating_off.png" />
		</c:if>
		</c:forTokens>
		-<b>${review.headline}</b>
		</td>
		</tr>
		<tr>
		<td>
		by ${review.customer.fullname} on ${review.reviewTime}
		</td>
		</tr>
		<tr><td><i>${review.comment}</i></td></tr>
		<tr><td>&nbsp;</td></tr>
		</c:forEach>
		</table>
		</td>
		</tr>
		</table>
	</div>


	<jsp:directive.include file="footer.jsp" />
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			$("#buttonWriteReview").click(function() {
				window.location = 'write_review?tour_id=' + ${tour.tourId};
			});

			$("#buttonAddToCart").click(function() {
				window.location = 'add_to_cart?tour_id=' + ${tour.tourId};
			});
		});
	</script>

</body>
</html>