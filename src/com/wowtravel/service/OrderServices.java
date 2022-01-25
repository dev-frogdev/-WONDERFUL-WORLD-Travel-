package com.wowtravel.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wowtravel.controller.frontend.shoppingcart.ShoppingCart;
import com.wowtravel.dao.OrderDAO;
import com.wowtravel.entity.Customer;
import com.wowtravel.entity.OrderDetail;
import com.wowtravel.entity.Tour;
import com.wowtravel.entity.TourOrder;

public class OrderServices {
private OrderDAO orderDao;
private HttpServletRequest request;
private HttpServletResponse response;

public OrderServices(HttpServletRequest request, HttpServletResponse response) {
	this.orderDao = new OrderDAO();
	this.request = request;
	this.response = response;
}

public void listAllOrder() throws ServletException, IOException {
	listAllOrder(null);
}
public void listAllOrder(String message) throws ServletException, IOException {
List<TourOrder> listOrder = orderDao.listAll();

if (message != null) {
	request.setAttribute("message", message);
}

request.setAttribute("listOrder", listOrder);
String listPage = "order_list.jsp";
RequestDispatcher dispatcher = request.getRequestDispatcher(listPage);
dispatcher.forward(request, response);
}

public void viewOrderDetailForAdmin() throws ServletException, IOException {
	int orderId = Integer.parseInt(request.getParameter("id"));
	
	TourOrder order = orderDao.get(orderId);
	request.setAttribute("order", order);
	
	String detailPage = "order_detail.jsp";
	RequestDispatcher dispatcher = request.getRequestDispatcher(detailPage);
	dispatcher.forward(request, response);
}

public void showCheckoutForm() throws ServletException, IOException {
	String checkOutPage = "frontend/checkout.jsp";
	RequestDispatcher dispatcher = request.getRequestDispatcher(checkOutPage);
	dispatcher.forward(request, response);
}

public void placeOrder() throws ServletException, IOException {
	String fullname = request.getParameter("fullname");
	String phoneNumber = request.getParameter("phoneNumber");
	String dateOfBirth = request.getParameter("dateOfBirth");
	String passportNumber = request.getParameter("passportNumber");
	String passportDate = request.getParameter("passportDate");
	String passportCountry = request.getParameter("passportCountry");
	String passportAuthority = request.getParameter("passportAuthority");
	String paymentMethod = request.getParameter("paymentMethod");
	String sendTo = dateOfBirth + ", " + passportNumber + ", " + passportDate + ", " + passportCountry + ", " + passportAuthority;
	
	TourOrder order = new TourOrder();
	order.setFullname(fullname);
	order.setPhoneNumber(phoneNumber);
	order.setSendTo(sendTo);
	order.setPaymentMethod(paymentMethod);
	
	HttpSession session = request.getSession();
	Customer customer = (Customer) session.getAttribute("loggedCustomer");
	order.setCustomer(customer);
	
	ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
	Map<Tour, Integer> items = shoppingCart.getItems();
	
	Iterator<Tour> iterator = items.keySet().iterator();
	
	Set<OrderDetail> orderDetails = new HashSet<>();
	
	while (iterator.hasNext()) {
		Tour tour = iterator.next();
		Integer quantity = items.get(tour);
		float subtotal = quantity * tour.getPrice();
		
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setTour(tour);
		orderDetail.setTourOrder(order);
		orderDetail.setQuantity(quantity);
		orderDetail.setSubtotal(subtotal);
		
		orderDetails.add(orderDetail);
				
	}
	
	order.setOrderDetails(orderDetails);
	order.setOrderTotal(shoppingCart.getTotalAmount());
	
	orderDao.create(order);
	
	shoppingCart.clear();
	
	String message = "Thank you. Your order has been received."
	+ "We will deliver your tours within afew days.";
	request.setAttribute("message", message);
		
	String messagePage = "frontend/message.jsp";
	RequestDispatcher dispatcher = request.getRequestDispatcher(messagePage);
	dispatcher.forward(request, response);

}

public void listOrderByCustomer() throws ServletException, IOException {
	HttpSession session = request.getSession();
	Customer customer = (Customer) session.getAttribute("loggedCustomer");
	List<TourOrder> listOrders = orderDao.listByCustomer(customer.getCustomerId());
	
	request.setAttribute("listOrders", listOrders);
	
	String historyPage = "frontend/order_list.jsp";
	RequestDispatcher dispatcher = request.getRequestDispatcher(historyPage);
	dispatcher.forward(request, response);
	}

public void showOrderDetailForCustomer() throws ServletException, IOException {
int orderId = Integer.parseInt(request.getParameter("id"));

HttpSession session = request.getSession();
Customer customer = (Customer) session.getAttribute("loggedCustomer");

	TourOrder order = orderDao.get(orderId, customer.getCustomerId());
	request.setAttribute("order", order);
	
	String detailPage = "frontend/order_detail.jsp";
	RequestDispatcher dispatcher = request.getRequestDispatcher(detailPage);
	dispatcher.forward(request, response);
}

public void showEditOrderForm() throws ServletException, IOException {
	Integer orderId = Integer.parseInt(request.getParameter("id"));
	
	HttpSession session = request.getSession();
	Object isPendingTour = session.getAttribute("NewTourPendingToAddToOrder");
	
	if (isPendingTour == null) {
	TourOrder order = orderDao.get(orderId);
	session.setAttribute("order", order);
	} else {
		session.removeAttribute("NewTourPendingToAddToOrder");
	}
	
	String editPage = "order_form.jsp";
	RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
	dispatcher.forward(request, response);
}

public void updateOrder() throws ServletException, IOException {
	HttpSession session = request.getSession();
	TourOrder order = (TourOrder) session.getAttribute("order");
	
	String fullname = request.getParameter("fullname");
	String phoneNumber = request.getParameter("phoneNumber");
	String sendTo = request.getParameter("sendTo");
	String paymentMethod = request.getParameter("paymentMethod");
	String orderStatus = request.getParameter("orderStatus");
	
	order.setFullname(fullname);
	order.setPhoneNumber(phoneNumber);
	order.setSendTo(sendTo);
	order.setPaymentMethod(paymentMethod);
	order.setOrderStatus(orderStatus);
	
	String[] arrayTourId = request.getParameterValues("tourId");
	String[] arrayPrice = request.getParameterValues("price");
	String[] arrayQuantity = new String[arrayTourId.length];
	
	for (int i = 1; i <= arrayQuantity.length; i++) {
		arrayQuantity[i - 1] = request.getParameter("quantity" + i);
	}
	
	Set<OrderDetail> orderDetails = order.getOrderDetails();
	orderDetails.clear();
	
	float totalAmount = 0.0f;
	
	for (int i = 0; i <= arrayTourId.length; i++) {
	int tourId = Integer.parseInt(arrayTourId[i]);
	int quantity = Integer.parseInt(arrayQuantity[i]);
	float price = Float.parseFloat(arrayPrice[i]);
	
	float subtotal = price * quantity;
	
	OrderDetail orderDetail = new OrderDetail();
	orderDetail.setTour(new Tour(tourId));
	orderDetail.setQuantity(quantity);
	orderDetail.setSubtotal(subtotal);
	orderDetail.setTourOrder(order);
	
	orderDetails.add(orderDetail);
	
	totalAmount += subtotal;
		}
		order.setOrderTotal(totalAmount);
		orderDao.update(order);
		String message = "The order " + order.getOrderId() + " has been update successfully";

		listAllOrder(message);

	}

public void deleteOrder() throws ServletException, IOException {
	Integer orderId = Integer.parseInt(request.getParameter("id"));
	orderDao.delete(orderId);
	
	String message = "The order ID" + orderId + "has been deleted.";
	listAllOrder(message);
}


}

