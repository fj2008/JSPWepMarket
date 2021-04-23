<%@page import="exception.DuplicateProductException"%>
<%@page import="DAO.ProductRepository"%>
<%@page import="DTO.Product"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file = "/config.jsp" %>
<%	
	String productId=request.getParameter("productId");
	String name = request.getParameter("name");
	String unitPrice = request.getParameter("unitPrice");
	String description = request.getParameter("description");
	String manufacturer = request.getParameter("manufacturer");
	String category = request.getParameter("category");
	String unitsInStock =request.getParameter("unitsInStock");
	String condition = request.getParameter("condition");
	
	
	Product product = new Product(
			productId, name, unitPrice, description, manufacturer, 
			category, unitsInStock, condition
			);	//상품 정보 저장
	//productRepository객체의 생성을 제한 (싱글턴 패턴)
	ProductRepository pr = ProductRepository.getInstance();
	try{
		pr.addProduct(product);
		
		response.sendRedirect( PRODUCT_PAGE_URL);
	}catch(DuplicateProductException e){
		response.sendRedirect(DUPLICATE_PRODUCT_PAGE_URL);
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<title>상품 목록</title>
	<!--  -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!--  -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
		<!--  -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<!--  -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

	
<hr>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>