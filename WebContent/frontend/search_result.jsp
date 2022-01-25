<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Results for ${keyword} - WOW Travel</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<c:if test="${fn:length(result) == 0}">
			<h2>No Results for "${keyword}"</h2>
		</c:if>
		<c:if test="${fn:length(result) > 0}">
			<br></br>
			<div align="center" style="width: 80 %; margin: 0 auto;">
			<center><h2>Results for "${keyword}":</h2></center>
			<div>
					<c:forEach items="${result}" var="tour">
					<div>
						<div style = "display: inline-block; margin: 20px; wigth: 10%;">
							<div align="left">
								<a href="view_tour?id=${tour.tourId}"> <img
									class="tour-small"
									src="data:image/jpg;base64,${tour.baseImage}" />
								</a>
							</div>
						</div>
						<div style = "display: inline-block; margin: 20px; vertical-align: top; wigth: 70%;" align="left">
							<div>
								<h2><a href="view_tour?id=${tour.tourId}"> <b>${tour.title}</b></h2>
								</a>
							</div>
							<div>Rating *****</div>
							<div>
								<i>by ${tour.cityCountry}</i>
							</div>
							<div>
								<p>${fn:substring(tour.depiction, 0, 100)}...</p>
							</div>
							</div>
							<div style = "display: inline-block; margin: 20px; vertical-align: top;">
							<h3>${tour.price} грн.</h3>
							<h3><a href="">Add to Cart</a></h3>						
						</div>
				</div>
				</c:forEach>
			</div>
		</c:if>
	</div>
	<br></br><br></br></br><br></br></br><br></br>
	<jsp:directive.include file="footer.jsp" />

</body>
</html>