package service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DTO.Product;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/cart/add")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자가 장바구니에 추가할 상품 번호를 전달하면
		// 해당 번호를 저장한다.
		
		
		//사용자의 장바구니 정보를 갖고 있는 세션을 가져온다.
		HttpSession session = request.getSession();
		
	
		//세션이 생성된 시간을 반환(유닉스타임스탬프로반환)
//		System.out.println(session.getCreationTime());
		//세션의 id반환
//		System.out.println(session.getId());
		//세션의 생성 여부(처음 생성된 세션이면 true,이전에 생성된 세션이면 false를 반환)
//		System.out.println(session.isNew());
		
		//세선에서 장바구니(goodsList)를 꺼낸다
		ArrayList<String> goodsList = (ArrayList<String>) session.getAttribute("goodsList");//attribute가 값을 받지 못하면 null반환
		if(goodsList == null) {
			goodsList = new ArrayList<String>();
		}
		String productId= request.getParameter("productId");//장바구니에 추가할 상품의 아이디
		
		goodsList.add(productId);//저장

		session.setAttribute("goodsList", goodsList);//장바구니에 들어있는 리스트를 다시 세션에 저장
		
		response.setStatus(204);//응답코드지정
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
