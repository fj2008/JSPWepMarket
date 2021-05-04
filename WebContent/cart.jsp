<%@page import="DAO.ProductRepository"%>
<%@page import="DTO.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
			<title>장 바 구 니</title>
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
	<jsp:include page="header.jsp" />
	
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">상품 정보</h1>
		</div>
	</div>
	<div class="containes">
		<div class="row">
			<table width="100%">
				<tr>
					<td align="left">
						<a href="/WebMarket/cart/delete/all" class="btn btn-danger">삭제하기</a>
					</td>
					<td align="right">
						<a href="/WebMarket/cart/order" class="btn btn-success">주문하기</a>
					</td>
				</tr>
			</table>
		</div>
		<div style="padding-top : 50px;">
			<table class="table table-hover">
				<tr>
					<th>상품</th>
					<th>가격</th>
					<th>소계</th>
					<th>비고</th>
				</tr>
				
				<%
					
					ProductRepository pr = ProductRepository.getInstance();
				
					ArrayList<String> elementList= (ArrayList<String>) session.getAttribute("goodsList");
					if(elementList ==null){
						elementList = new ArrayList<String>();
					}
					//장바구니에 넣은 상품의 총 가격이 얼마인지
					int total = 0;
					if(elementList.size() == 0){
				%>		
					
					<tr>
						<th colspan="4" style="text-align: center;">장바구니에 담긴 상품이 없습니다.</th>
					</tr>
					
				<%	
				}
					
					for(String productId : elementList){
						//장바구니에 넣은 n번째 상품의 정보
						Product product = pr.getProduct(productId);
						
						total += product.getUnitPrice();
				%>
				<tr>
					<td><%=product.getName() %></td>
					<td><%=product.getUnitPrice() %></td>
					<td>1 x <%=product.getUnitsInStock()%></td>
					<td><a href="#" onclick="removeCart('<%=product.getProductId() %>')" class="badge badge-danger">삭제</a></td>
				</tr>
				
				<%		
					}
				%>
				<tr>
					<th colspan="2"> 총액 : </th>
					<th colspan="2"> <%=total %> </th>
				</tr>
			</table>
		</div>
	</div>
	
	<jsp:include page="footer.jsp" />
	<script>
		function removeCart(productId){
			$.ajax({
				url: "http://192.168.2.28:8081/WebMarket/cart/delete",
				date: "productId="+productId,
				success: function(){
					alert("상품을 삭제 했습니다.");
					
					location.reload();//location.reload페이지 세로고침 기능
				},
				error : function(){
					alert("상품을 삭제하지 못했습니다.\n잠시 후 다시 시도해주세요.")
				}
				
			})
			//자바 스크립트의 cos정책 - 다른 도메인의 프로그램은 실행시킬 수 없다.
		//Run on Server로 웹페이지를 열면 localhost도메인으로 접근하기 때문에 자바스크립의 ajax에 우리가 url로 써놓은 아이피의 프래그램을 실행 시킬 수 없음
		}
		
	</script>
</body>
</html>