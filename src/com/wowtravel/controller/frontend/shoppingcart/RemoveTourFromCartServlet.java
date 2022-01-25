package com.wowtravel.controller.frontend.shoppingcart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wowtravel.dao.TourDAO;
import com.wowtravel.entity.Tour;

@WebServlet("/remove_from_cart")
public class RemoveTourFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public RemoveTourFromCartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer tourId = Integer.parseInt(request.getParameter("tour_id"));
		
		Object cartObject = request.getSession().getAttribute("cart");
		
		ShoppingCart shoppingCart = (ShoppingCart) cartObject;

		shoppingCart.removeItem(new Tour(tourId));
		
		String cartPage = request.getContextPath().concat("/view_cart");
		response.sendRedirect(cartPage);
		
	}
}
