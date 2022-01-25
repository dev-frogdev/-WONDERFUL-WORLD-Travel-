package com.wowtravel.controller.admin.order;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wowtravel.entity.OrderDetail;
import com.wowtravel.entity.TourOrder;

@WebServlet("/admin/remove_tour_from_order")
public class RemoveTourFromOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RemoveTourFromOrderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	int tourId = Integer.parseInt(request.getParameter("id"));
	HttpSession session = request.getSession();
	TourOrder order = (TourOrder) session.getAttribute("order");
	
	Set<OrderDetail> orderDetails = order.getOrderDetails();
	Iterator<OrderDetail> iterator = orderDetails.iterator();
	
	while (iterator.hasNext()) {
		OrderDetail orderDetail = iterator.next();
		if (orderDetail.getTour().getTourId() == tourId) {
			float newOrderTotal = order.getOrderTotal() - orderDetail.getSubtotal();
			order.setOrderTotal(newOrderTotal);
			iterator.remove();
		}
	}
	
	String editOrderFormPage = "order_form.jsp";
	RequestDispatcher dispatcher = request.getRequestDispatcher(editOrderFormPage);
	dispatcher.forward(request, response);
	}

}
