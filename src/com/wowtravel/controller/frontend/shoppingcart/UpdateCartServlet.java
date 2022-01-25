package com.wowtravel.controller.frontend.shoppingcart;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/update_cart")
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public UpdateCartServlet() {
        super();

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] arrayTourIds = request.getParameterValues("tourId");
		String[] arrayQuantities = new String[arrayTourIds.length];

		for (int i = 1; i <= arrayQuantities.length; i++) {
			String aQuantity = request.getParameter("quantity" + i);
			arrayQuantities[i - 1] = aQuantity;
			}
		try {
			int[] tourIds = Arrays.stream(arrayTourIds).mapToInt(Integer::parseInt).toArray();
			int[] quantities = Arrays.stream(arrayQuantities).mapToInt(Integer::parseInt).toArray();
			ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
			cart.updateCart(tourIds, quantities);
		} catch (NumberFormatException e) {
			
		}
		
		String cartPage = request.getContextPath().concat("/view_cart");
		response.sendRedirect(cartPage);
		}
}
