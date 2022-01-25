<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WOW Travel</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div class="center">
	<div>
		<br></br>
		<h2>New Tours:</h2>
		<c:forEach items="${listNewTours}" var="tour">
		<div class="tour">
		<div>
		<a href="view_tour?id=${tour.tourId}">
		<img class = "tour-small" 
		src="data:image/jpg;base64,${tour.baseImage}" />
		</a>
		</div>
		<div>
		<a href="view_tour?id=${tour.tourId}"> <b>${tour.title}</b>
		</a>
		</div>
		<div>
		<jsp:directive.include file="tour_rating.jsp" />
		</div>
		<div>
		<i>by ${tour.cityCountry}</i>
		</div>
		<div>
		<b>${tour.price} грн.</b>
		</div>
</div>
		</c:forEach>
		</div>
		<div class="next-row">
			<h2>Best-Selling Tours:</h2>
			<c:forEach items="${listBestSellingTours}" var="tour">
				<div class="tour">
		<div>
		<a href="view_tour?id=${tour.tourId}">
		<img class = "tour-small" 
		src="data:image/jpg;base64,${tour.baseImage}" />
		</a>
		</div>
		<div>
		<a href="view_tour?id=${tour.tourId}"> <b>${tour.title}</b>
		</a>
		</div>
		<div>
		<jsp:directive.include file="tour_rating.jsp" />
		</div>
		<div>
		<i>by ${tour.cityCountry}</i>
		</div>
		<div>
		<b>${tour.price} грн.</b>
		</div>
</div>
			</c:forEach>
		</div>
		<div class="next-row">
			<h2>Most-Favored Tours:</h2>
			<c:forEach items="${listFavoredTours}" var="tour">
				<div class="tour">
		<div>
		<a href="view_tour?id=${tour.tourId}">
		<img class = "tour-small" 
		src="data:image/jpg;base64,${tour.baseImage}" />
		</a>
		</div>
		<div>
		<a href="view_tour?id=${tour.tourId}"> <b>${tour.title}</b>
		</a>
		</div>
		<div>
		<jsp:directive.include file="tour_rating.jsp" />
		</div>
		<div>
		<i>by ${tour.cityCountry}</i>
		</div>
		<div>
		<b>${tour.price} грн.</b>
		</div>
</div>
			</c:forEach>
		</div>
			<br></br>
	</div>
	<br></br>
	<jsp:directive.include file="footer.jsp" />

</body>
</html>