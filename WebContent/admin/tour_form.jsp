<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create new Tour</title>

<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet" href="../js/jquery-ui.min.css">
<link  rel="stylesheet" href="//netdna.bootstrapsdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="...//css/richtext.min.css">

<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
<script type="text/javascript" src="../js/jquery.richtext.min.js"></script>
</head>
<body>
<jsp:directive.include file="header.jsp" />

	<div align="center">
		<h2 class="pageheading">
		<c:if test="${tour != null}">
		Edit Tour
		</c:if>
		<c:if test="${tour == null}">
		Create New Tour
		</c:if>
	</h2>
	</div>

	<div align="center">
		<c:if test="${tour != null}">
			<form action="update_tour" method="post" id="tourForm"  enctype="multipart/form-data" >
			<input type="hidden" name="tourId" value="${tour.tourId}">
		</c:if>
		<c:if test="${tour == null}">
			<form action="create_tour" method="post" id="tourForm" enctype="multipart/form-data" >
		</c:if>
		
		<table class="form">
			<tr>
				<td>Category:</td>
				<td><select name="category">
					<c:forEach items="${listCategory}" var="category">
						
								<option value="${category.categoryId}">
				
							${category.name}
							</option>
					</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td align="right">Title:</td>
				<td align="left"><input type="text" id="title"
					name="title" size="20" value="${tour.title}" /></td>
			</tr>
			<tr>
				<td align="right">City, Country:</td>
				<td align="left"><input type="text" id="cityCountry"
					name="cityCountry" size="20" value="${tour.cityCountry}" /></td>
			</tr>
			<tr>
				<td align="right">Product Code:</td>
				<td align="left"><input type="text" id="productCode"
					name="productCode" size="20" value="${tour.productCode}" /></td>
			</tr>
			<tr>
				<td align="right">Publish Date:</td>
				<td align="left"><input type="text" id="publishDate"
					name="publishDate" size="20" value="<fmt:formatDate pattern='MM/dd/yyyy' value='${tour.publishDate}'/>" /></td>
			</tr>
			<tr>
				<td align="right">Tour Image:</td>
				<td align="left">
				<input type="file" id="tourImage"
					name="tourImage" size="20" /><br/>
				<img id="thumbnail" alt="Image Preview" style="width:20%; margin-top: 10px" src="data:image/jpg;base64,${tour.baseImage}" />
				</td>
			</tr>
			<tr>
				<td align="right">Price:</td>
				<td align="left"><input type="text" id="price"
					name="price" size="20" value="${tour.price}" /></td>
			</tr>
			<tr>
				<td align="right">Description:</td>
				<td align="left">
				<textarea rows="5" cols="50" name="depiction" id="depiction">${tour.depiction}</textarea>
				</td>
				</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<button type="submit">Save</button>&nbsp;&nbsp;&nbsp; 
				<button id="buttonCancel">Cancel</button>
				</td>
			</tr>
		</table>
		</form>
	</div>

	<jsp:directive.include file="footer.jsp" />
	</body>
	<script type="text/javascript">
	
	$(document).ready(function() {
		$('#publishDate').datepicker();
		$('#tourImage').change(function(){
			showImageThumbnail(this);
		});
		
		$("#tourForm").validate({
			rules: {
		category: "required",
		title: "required",
		cityCountry: "required",	
		productCode: "required",
		publishDate: "required",
		price: "required",	
		depiction: "required",
		<c:if test= "${tour == null}">
		tourImage: "required",
		</c:if>
		
		
		},
			
			messages: {
				category: "Please select a category for the tour",
				title: "Please enter title of the tour",
				cityCountry: "Please enter city and country of the tour",
				productCode: "Please enter product code of the tour",
				publishDate: "Please enter publish date of the tour",
				tourImage: "Please enter image of the tour",
				price: "Please enter price of the tour",
				depiction: "Please enter description of the tour" 
			}
		})
	
		$("#buttonCancel").click(function() {
			history.go(-1);
		});
	});	
	
	function showImageThumbnail(fileInput) {
		var file = fileInput.files[0];
		
		var reader = new FileReader();
		reader.onload = function(e) {
			$('#thumbnail').attr('src', e.target.result);
		};
		
		reader.readAsDataURL(file);
}
	</script>
</html>