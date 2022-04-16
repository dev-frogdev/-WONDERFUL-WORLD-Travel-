package com.wowtravel.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.wowtravel.entity.Customer;
import com.wowtravel.entity.OrderDetail;
import com.wowtravel.entity.OrderDetailId;
import com.wowtravel.entity.Tour;
import com.wowtravel.entity.TourOrder;

class OrderDAOTest {

	private static OrderDAO orderDAO;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		orderDAO = new OrderDAO();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		orderDAO.close();
	}
	
	@Test
	void testCreateTourOrder() {
		TourOrder order = new TourOrder();
		Customer customer = new Customer();
		customer.setCustomerId(2);
	
		order.setCustomer(customer);
		order.setFullname("Iryna");
		order.setSomePhoneNumber("0000000000");
		order.setSendTo("Kharkiv, Ukraine");
				
		Set<OrderDetail> orderDetails = new HashSet<>();
		OrderDetail orderDetail = new OrderDetail();
		
		Tour tour= new Tour(30);
		orderDetail.setTour(tour);
		orderDetail.setQuantity(1);
		orderDetail.setSubtotal(11.0f);
		orderDetail.setTourOrder(order);		
		
		orderDetails.add(orderDetail);
		
		order.setOrderDetails(orderDetails);
		
		orderDAO.create(order);
		
		assertTrue(order.getOrderId() > 0);
		}
	

	@Test
	void testUpdateTourOrderSendTo() {
		Integer orderId = 3;
		TourOrder order = orderDAO.get(orderId);
		order.setSendTo("New Address");
		orderDAO.update(order);
		TourOrder updatedOrder = orderDAO.get(orderId);
		assertEquals(order.getSendTo(), updatedOrder.getSendTo());
	}

	@Test
	void testUpdateTourOrderDetail() {
		Integer orderId = 3;
		TourOrder order = orderDAO.get(orderId);
		
		Iterator <OrderDetail> iterator = order.getOrderDetails().iterator();
		
		while (iterator.hasNext()) {
			OrderDetail orderDetail = iterator.next();
			if (orderDetail.getTour().getTourId() == 3) {
				orderDetail.setQuantity(1);
				orderDetail.setSubtotal(4);
			}
		}
			
		orderDAO.update(order);
		
		TourOrder updatedOrder = orderDAO.get(orderId);
		
		iterator = order.getOrderDetails().iterator();
		
		int expectedQuantity = 1;
		float expectedSubtotal = 4;
		int actualQuantity = 0;
		float actualSubtotal = 0;
		
		while (iterator.hasNext()) {
			OrderDetail orderDetail = iterator.next();
			if (orderDetail.getTour().getTourId() == 88) {
				actualQuantity = orderDetail.getQuantity();
				actualSubtotal = orderDetail.getSubtotal();
			}
		}
		
		assertEquals(expectedQuantity, actualQuantity, 1);
		assertEquals(expectedSubtotal, actualSubtotal, 4);
		
	}
	
	@Test
	void testGet() {
		Integer orderId = 1;
		TourOrder order = orderDAO.get(orderId);
		System.out.println(order.getFullname());
		System.out.println(order.getSomePhoneNumber());
		System.out.println(order.getSendTo());
		System.out.println(order.getOrderStatus());
		System.out.println(order.getOrderTotal());
		System.out.println(order.getPaymentMethod());
		
		assertEquals(true, order != null);
	}
	
	@Test
	public void testGetByIdAndCustomerNull() {
		Integer orderId = 10;
		Integer customerId = 100;
		
		TourOrder order = orderDAO.get(orderId, customerId);
		
		assertNull(order);
	}
	
	@Test
	public void testGetByIdAndCustomerNotNull() {
		Integer orderId = 93;
		Integer customerId = 28;
		
		TourOrder order = orderDAO.get(orderId, customerId);
		
		assertNotNull(order);
	}

	@Test
	void testDeleteOrder() {
		int orderId = 92;
		orderDAO.delete(orderId);
		
		TourOrder order = orderDAO.get(orderId);
		
		assertNull(order);
	}
	
	@Test
	void testListAll() {
		List<TourOrder> listOrders = orderDAO.listAll();
		
		  for (TourOrder order : listOrders) { 
			  System.out.println(order.getOrderId() +
		  " - " + order.getCustomer().getFullname() + " - " + order.getOrderTotal() +
		  " - " + order.getOrderStatus() ); 
			  for (OrderDetail detail : order.getOrderDetails())	{
				  Tour tour = detail.getTour();
				  int quantity = detail.getQuantity();
				  float subtotal = detail.getSubtotal();
				  System.out.println("\t" + tour.getTitle() + " - " + quantity + " - " + subtotal);
			  }
		  }
		
		 assertTrue(listOrders.size() > 0);
	}
	
	@Test
	public void testListByCustomerNoOrders() {
		Integer customerId = 30;
		List<TourOrder>	listOrders = orderDAO.listByCustomer(customerId);
		
		assertTrue(listOrders.isEmpty());
	
	}
	
	@Test
	public void testListByCustomerHaveOrders() {
		Integer customerId = 28;
		List<TourOrder>	listOrders = orderDAO.listByCustomer(customerId);
		
		assertTrue(listOrders.size() > 0);
	
	}
	
	@Test
	void testCount() {
		long totalOrders = orderDAO.count();
		assertEquals(10, totalOrders);
	}
	
	@Test
	public void testListMostRecentSales() {
		List<TourOrder> recentOrders = orderDAO.listMostRecentSales();
		
		assertEquals(3, recentOrders.size());
		}

	}
