package product;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Product;
import util.DBMng;

/**
 * Servlet implementation class ProductInfo
 */
@WebServlet("/Product/info")
public class ProductInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String p_id = request.getParameter("p_id");
			
			Connection conn = DBMng.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement("SELETE * FORM product WHERE p_id =?");
			
			pstmt.setString(1, p_id);
			
			ResultSet rs = pstmt.executeQuery();
			
			boolean isExist = rs.next();
			
			if(isExist) {
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
				request.setAttribute("product", product);
				
				RequestDispatcher dis = request.getRequestDispatcher("/product/info.jsp");
				dis.forward(request, response);
			}else {
				response.setStatus(400);
			}
			
		
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
