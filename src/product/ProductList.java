package product;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Product;
import util.DBMng;

/**
 * Servlet implementation class ProductList
 */
@WebServlet("/product/list")
public class ProductList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * ��ǰ ��� �������� �ʿ��� ��ǰ�� ����� db���� ������
	 * request�� ��Ƽ� �������Ѵ�.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = DBMng.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM product");
			
			ResultSet rs = pstmt.executeQuery();
			//View�� ������ ��ǰ�� ���
			List<Product> productList = new ArrayList<>();
			
			while(rs.next()) {
				//n��° ��ǰ�� ��Ͽ� ����
				
				String p_id =rs.getString("p_id");//n��° ��ǰ�� p_id Į���� ���� ����
				
				
				//n��° ��ǰ�� p_name Į���� ���� ����
				String p_name = rs.getString("p_name");
				//n��° ��ǰ�� unitPrice Į���� ���� ����
				int p_unitPrice =rs.getInt("p_unitPrice");
				//n��° ��ǰ�� description Į���� ���� ����
				String p_description = rs.getString("p_description");
				//n��° ��ǰ�� manufacturer Į���� ���� ����
				String p_manufacturer = rs.getString("p_manufacturer");
				//n��° ��ǰ�� category Į���� ���� ����
				String p_category = rs.getString("p_category");
				//n��° ��ǰ�� unitsInStock Į���� ���� ����
				int p_unitsInStock =rs.getInt("p_unitsInStock");
				//n��° ��ǰ�� condition Į���� ���� ����
				String p_condition =rs.getString("p_condition");
				//n��° ��ǰ�� imagePath Į���� ���� ����
				String p_imagePath =rs.getString("p_imagePath");
				
				
				
				Product product = new Product(p_id, p_name, p_unitPrice,p_description,p_manufacturer,
								p_category,p_unitsInStock,p_condition,p_imagePath);//n��° ��ǰ�� �����͸� ������ ��ü ����
				
				productList.add(product);//�� �ڷḦ ����Ʈ�� ����
			}//end while
			
			request.setAttribute("productList", productList);
			
			RequestDispatcher dis = request.getRequestDispatcher("/product/list.jsp");
			dis.forward(request, response);
			
		} catch (SQLException e) {
			RequestDispatcher dis = request.getRequestDispatcher("/error/500.jsp");
			
			dis.forward(request, response);
		} finally {
			DBMng.closeConnection();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
