package com.wowtravel.controller.frontend.shoppingcart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wowtravel.dao.TourDAO;
import com.wowtravel.entity.Tour;

@WebServlet("/add_to_cart")
public class AddTourToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddTourToCartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Integer tourId = Integer.parseInt(request.getParameter("tour_id"));
	
	Object cartObject = request.getSession().getAttribute("cart");
	
	ShoppingCart shoppingCart = null;;
	
	if (cartObject !=null && cartObject instanceof ShoppingCart) {
		shoppingCart = (ShoppingCart) cartObject;
	}else{
		shoppingCart = new ShoppingCart();
		request.getSession().setAttribute("cart", shoppingCart);
		}
	
	TourDAO tourDAO = new TourDAO();
	Tour tour = tourDAO.get(tourId);
	
	shoppingCart.addItem(tour);
	
	String cartPage = request.getContextPath().concat("/view_cart");
	response.sendRedirect(cartPage);
	
	}
}

