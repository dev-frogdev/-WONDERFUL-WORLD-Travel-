<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Tour To Order</title>
</head>
<body>
<div align="center">
<h2>
The tour <i>${tour.title}</i> has been added to the order ID <b>${order.orderId}</b>
</h2>
<input type="button" value="Close" onclick="javascript: self.close();" />
</div>
<script type="text/javascript">
window.onunload = function() {
	window.opener.locaton.reload();
}
</script>
</body>
</html>