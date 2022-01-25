package com.wowtravel.controller.frontend.shoppingcart;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.wowtravel.entity.Tour;

public class ShoppingCart {
private Map<Tour, Integer> cart = new HashMap<>();

public void addItem(Tour tour) {
	if (cart.containsKey(tour)) {
		Integer quantity = cart.get(tour) + 1;
		cart.put(tour, quantity);
	} else { 
		cart.put(tour, 1);
	}
}

public void removeItem(Tour tour) {
	cart.remove(tour);
}

public int getTotalQuantity() {
	int orderTotal = 0;
	
	Iterator<Tour> iterator = cart.keySet().iterator();
	
	while (iterator.hasNext()) {
		Tour next = iterator.next();
		Integer quantity = cart.get(next);
		orderTotal += quantity;
	}
	
	return orderTotal;
}

public float getTotalAmount() {
	float orderTotal = 0.0f;
	
	Iterator<Tour> iterator = cart.keySet().iterator();
	
	while (iterator.hasNext()) {
		Tour tour = iterator.next();
		Integer quantity = cart.get(tour);
		double subTotal = quantity * tour.getPrice();
		orderTotal += subTotal;
	}
	
	return orderTotal;
	
}

public void updateCart(int[] tourIds, int[] quantities) {
	for (int i = 0; i < tourIds.length; i++) {
		Tour key = new Tour(tourIds[i]);
		Integer value = quantities[i];
		cart.put(key, value);
	}
}

public void clear( ) {
	cart.clear();
}

public int getTotalItems() {
	return cart.size();
}

public Map<Tour, Integer> getItems() {
	return this.cart;
}

}
