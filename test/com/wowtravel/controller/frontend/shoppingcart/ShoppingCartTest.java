package com.wowtravel.controller.frontend.shoppingcart;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.wowtravel.entity.Tour;

class ShoppingCartTest {
	private static ShoppingCart cart;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cart = new ShoppingCart();
		Tour tour = new Tour();
		
		cart.addItem(tour);
		cart.addItem(tour);
	}
	
	
	@Test
	public void testAddItem() {
		
		Map<Tour, Integer> items = cart.getItems();
		int quantity = items.get(new Tour(12));
		
		assertEquals(1, quantity);
	}
	
	@Test
	public void testRemoveItem() {
		cart.removeItem(new Tour(1));
		
		assertTrue(cart.getItems().isEmpty());
	}
	
	@Test
	public void testRemoveItem2() {
		Tour tour2 = new Tour(2);		
		cart.addItem(tour2);
		
		cart.removeItem(new Tour(2));
		
		assertNull(cart.getItems().get(tour2));
	}
	
	@Test
	public void testGetTotalQuantity() {
		Tour tour3 = new Tour(3);
		cart.addItem(tour3);
		
		assertEquals(3, cart.getTotalQuantity());
	}
	
	@Test   
	public void testGetTotalAmount2() {
		assertEquals(20.0f, cart.getTotalAmount(), 0.0f);
	}
	
	@Test
	public void testClear() {
		cart.clear();
		assertEquals(0, cart.getTotalQuantity());
	}
	
	@Test
	public void testUpdateCart() {
		ShoppingCart cart = new ShoppingCart();
		Tour tour12 = new Tour(12);
		Tour tour18 = new Tour(18);
		
		cart.addItem(tour12);
		cart.addItem(tour18);
		
		int[] tourIds = {12, 18};
		int[] quantities = {1, 1};
		
		cart.updateCart(tourIds, quantities);
		
		assertEquals(1, cart.getTotalQuantity());
		
	}
}
