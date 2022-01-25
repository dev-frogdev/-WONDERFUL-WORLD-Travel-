package com.wowtravel.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wowtravel.entity.Customer;
import com.wowtravel.entity.Review;
import com.wowtravel.entity.Tour;

public class ReviewDAOTest {
	
	private static ReviewDAO reviewDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		reviewDao = new ReviewDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		reviewDao.close();
	}

	@Test
	public void testCreateReview() {
		Review review = new Review();
		Tour tour = new Tour();
		tour.setTourId(18);
		
		Customer customer = new Customer();
		customer.setCustomerId(2);
		
		review.setTour(tour);
		review.setCustomer(customer);
		
		review.setHeadline("This is a very good tour!");
		review.setRating(3);
		review.setComment("I have just read this tour. Very good.");
		
		Review savedReview = reviewDao.create(review);
		
		assertTrue(savedReview.getReviewId() > 0);
	}

	@Test
	public void testGet() {
		Integer reviewId = 1;
		Review review = reviewDao.get(reviewId);
		
		assertNotNull(review);
	}
	
	@Test
	public void testUpdateReview() {
		Integer reviewId = 1;
		Review review = reviewDao.get(reviewId);
		review.setHeadline("Excellent tour");
		
		Review updateReview = reviewDao.update(review);
		
		assertEquals(review.getHeadline(), updateReview.getHeadline());
	}

	@Test
	public void testDeleteObject() {
		int reviewId = 2;
		reviewDao.delete(reviewId);
		
		Review review = reviewDao.get(reviewId);
		
		assertNull(review);
		
	}

	@Test
	public void testListAll() {
		List<Review> listReview = reviewDao.listAll();
		
		for (Review review : listReview) {
			System.out.println(review.getReviewId() + " - " + review.getTour().getTitle()
					+ " - " + review.getCustomer().getFullname()
					+ " - " + review.getHeadline() + " - " + review.getRating());
		}
		assertTrue(listReview.size() > 0);
	}

	@Test
	public void testCount() {
		long totalReviews = reviewDao.count();
		System.out.println("Total Reviews: " + totalReviews);
		assertTrue (totalReviews > 0);
	}
	
	@Test
	public void findByCustomerAndTourFound() {
		Integer customerId = 4;
		Integer tourId = 18;
		
		Review result = reviewDao.findByCustomerAndTour(customerId, tourId);
		
		assertNull(result);
	}
}
