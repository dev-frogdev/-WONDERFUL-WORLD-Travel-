<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page Not Found Error</title>
</head>
<body>
<div>
<div>
<img src="${pageContext.request.contextPath}/images/WOWTravel.png">
</div>
<div>
<h2>Sorry, the requested page could not be found.</h2>
</div>
<div>
<a href="javascript:history.go(-1);">Go Back</a>
</div>

</div>
</body>
</html>