package service;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import DAO.ProductRepository;
import DTO.Product;
import exception.DuplicateProductException;

/**
 * Servlet implementation class ProductMng
 */
@WebServlet("/productMng")
public class ProductMng extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductMng() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String filename = "";
		String realFolder = "D:\\eclipse-workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\WebMarket\\images";
		
		int maxSize = 10* 1024*1024;
		String encType="UTF-8";
		
		MultipartRequest multi =new MultipartRequest(request, realFolder,maxSize,encType,new DefaultFileRenamePolicy());
		
		String productId= multi.getParameter("productId");
		String name = multi.getParameter("name");
		String unitPrice = multi.getParameter("unitPrice");
		String description = multi.getParameter("description");
		String manufacturer = multi.getParameter("manufacturer");
		String category = multi.getParameter("category");
		String unitsInStock = multi.getParameter("unitsInStock");
		String condition = multi.getParameter("condition");
		
		Enumeration<String> files = multi.getFileNames();
		String fileName = files.nextElement();
		fileName = multi.getFilesystemName(fileName);
		
		Product product = new Product(
				productId, name, unitPrice, description, manufacturer, 
				category, unitsInStock, condition, fileName
				);
		
		ProductRepository pr = ProductRepository.getInstance();
		
		
		try{
			pr.addProduct(product);//상품 정보 저장
			//foward는 url이동이아닌 그 페이지에서 제어만 이동을 한다
			response.sendRedirect("/WebMarket/products.jsp?active=products");
		}catch(DuplicateProductException e){//변수가 중복될때
			//변수값이 중복되지않아서 성공적으로 상품정보를 저장했다면
			response.sendRedirect("/WebMarket/error/duplicate.jsp?active=addProduct");
			//redirect는 페이지 자체가 이동됨
		}
		
	}

}
