<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tours in ${category.name} - Online Tours Store</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div class="center">
		<h2>${category.name}</h2>
	</div>

	<div class="tour-group">
		<c:forEach items="${listTours}" var="tour">
			<div class="tour">
				<div>
					<a href="view_tour?id=${tour.tourId}"> <img class="tour-small"
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

	<jsp:directive.include file="footer.jsp" />
</body>
</html>