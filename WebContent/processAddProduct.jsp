<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="exception.DuplicateProductException"%>
<%@page import="DAO.ProductRepository"%>
<%@page import="DTO.Product"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file = "/config.jsp" %>
<%	
	request.setCharacterEncoding("UTF-8");

	String filename = "";
	//프로젝트의 절대 경로
	String realFolder  = "D:\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\WebMarket\\images";
	//업로드될 파일의 최대 크기 5MB
	int maxSize = 10*1024*1024;
	//인코딩유형
	String encType = "UTF-8";
	
	MultipartRequest multi = new MultipartRequest(request,realFolder,maxSize,encType,new DefaultFileRenamePolicy());

	String productId=multi.getParameter("productId");
	String name = multi.getParameter("name");
	String unitPrice = multi.getParameter("unitPrice");
	String description = multi.getParameter("description");
	String manufacturer = multi.getParameter("manufacturer");
	String category = multi.getParameter("category");
	String unitsInStock =multi.getParameter("unitsInStock");
	String condition = multi.getParameter("condition");
	
	Enumeration files = multi.getFileNames();
	
	String fileName = (String) files.nextElement();
	
	fileName = multi.getFilesystemName(fileName);
	
	Product product = new Product(
			productId, name, unitPrice, description, manufacturer, 
			category, unitsInStock, condition, fileName
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