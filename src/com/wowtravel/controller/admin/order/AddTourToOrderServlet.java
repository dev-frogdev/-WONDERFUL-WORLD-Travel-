package com.wowtravel.controller.admin.order;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wowtravel.dao.TourDAO;
import com.wowtravel.entity.OrderDetail;
import com.wowtravel.entity.Tour;
import com.wowtravel.entity.TourOrder;

@WebServlet("/admin/add_tour_to_order")
public class AddTourToOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddTourToOrderServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int tourId = Integer.parseInt(request.getParameter("tourId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		TourDAO tourDao = new TourDAO();
		Tour tour = tourDao.get(tourId);
		
		HttpSession session = request.getSession();
		TourOrder order = (TourOrder) session.getAttribute("order");
		
		float subtotal = quantity * tour.getPrice();
		
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setTour(tour);
		orderDetail.setQuantity(quantity);
		orderDetail.setSubtotal(subtotal);
		
		float newTotal = order.getOrderTotal() + subtotal;
		order.setOrderTotal(newTotal);
		
		order.getOrderDetails().add(orderDetail);
		
		request.setAttribute("tour", tour);
		session.setAttribute("NewTourPendingToAddToOrder", true);
		
		String resultPage = "add_tour_result.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(resultPage);
		dispatcher.forward(request, response);
	}

}
