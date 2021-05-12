<%@page import="java.util.ArrayList"%>
<%@page import="DTO.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	List<Product> listOfProducts = (ArrayList<Product>)request.getAttribute("productList");


%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>상품 목록</title>
	<!-- 합쳐지고 최소화된 최신 CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<!-- 부가적인 테마 -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
	<!-- 부트스트랩을 위한 jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
	<%@ include file="../header.jsp" %>
	
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">상품 목록</h1>
		</div>
	</div>
	
	<div class="container">
		<div class="row" align="center">
			<!-- 상품을 하나씩 보여줌 -->
			<%
				for(int i=0; i<listOfProducts.size(); i++) {
					Product product = listOfProducts.get(i);
			%>
				<div class="col-md-4">
					<img src="../images/<%=product.getImagePath() %>">
					<h3><%=product.getName() %></h3>
					<p><%=product.getDescription() %></p>
					<p><%=product.getUnitPrice() %></p>
					<p><a href="<%= PRODUCT_PAGE_URL %>&p_id=<%=product.getProductId()%>" class="btn btn-secondary" role="button">상세 정보&raquo;</a></p>
				</div>
			<%
				}
			%>
		</div>
		
		<hr>
	</div>
	
	<%@ include file="../footer.jsp" %>
</body>
</html>
<!-- 
Servlet ->db에서 목록에 필요한 데이터를 불러오기
		->request dispatcher 상품목록 페이지로 포워딩
		
jsp  	->servler이 request에 담아서 전달한 목록 데이터를 사용해 출력


---------------------------------------------------------------
MVC모델 ->Serlvet이 기능을 담당 , JSP가 보여주는 역할
크루드서비스를 하나의 컨트롤러가 받아서 각각의 기능을 담당하는 파트로 보내는방식이있다
하지만 그런방식은 큰 프로그램에서 사용하고
우리는 크루드서비스를 하나하나 구현해서 사용한다

데이터베이스에 접근하는 개체를 모델이라고 한다
 -->






