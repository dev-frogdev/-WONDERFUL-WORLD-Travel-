<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Tour To Order ID</title>
</head>
<body>
<div align="center">
<h2>Add Tour To Order ID: ${order.orderId}</h2>
<form action="add_tour_to_order" method="post">
<table>
<tr>
<td>Select A Tour</td>
<td>
<select name="tourId">
<c:forEach items="${listTour}" var="tour" varStatus="status">
<option value="${tour.tourId}">${tour.title} - ${tour.cityCountry}</option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td>&nbsp;</td>
</tr>
<tr>
<td>Number Of Copies</td>
<td>
<select name="quantity">
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
</select>
</td>
</tr>
<tr><td>&nbsp;</td></tr>
<tr>
<td colspan="2" align="center">
<input type="submit" value="Add" />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" value="Cancel" onclick="javascript: self.close();" />
</td>
</tr>
</table>
</form>
</div>
</body>
</html>