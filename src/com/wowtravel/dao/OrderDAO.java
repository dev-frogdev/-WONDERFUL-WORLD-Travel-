package com.wowtravel.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.wowtravel.entity.Customer;
import com.wowtravel.entity.Tour;
import com.wowtravel.entity.TourOrder;

public class OrderDAO extends JpaDAO<TourOrder> implements GenericDAO<TourOrder> {

	@Override
	public TourOrder create(TourOrder order) {
		order.setOrderDate(new Date());
		order.setPaymentMethod("Card Payment");
		order.setOrderStatus("Registered");
		
		return super.create(order);
	}

	@Override
	public TourOrder update(TourOrder order) {
		return super.update(order);
	}
	
	@Override
	public TourOrder get(Object orderId) {
		return super.find(TourOrder.class, orderId);
	}

	public TourOrder get(Integer orderId, Integer customerId) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("orderId", orderId);
		parameters.put("customerId", customerId);
		
		List<TourOrder> result = super.findWithNamedQuery("TourOrder.findByIdAndCustomer", parameters);
		
		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}

	@Override
	public void delete(Object orderId) {
	super.delete(TourOrder.class, orderId);
	}

	@Override
	public List<TourOrder> listAll() {
		return super.findWithNamedQuery("TourOrder.findAll");
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("TourOrder.countAll");
	}
	
	public List<TourOrder> listByCustomer (Integer customerId) {
		return super.findWithNamedQuery("TourOrder.findByCustomer", "customerId", customerId);		
	}
	
	
	public List<TourOrder> listMostRecentSales() {
		return super.findWithNamedQuery("TourOrder.findAll", 0, 3);
	}

}
