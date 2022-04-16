package com.wowtravel.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wowtravel.entity.Category;
import com.wowtravel.entity.Tour;

public class TourDAOTest {
	
	private static TourDAO tourDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		tourDao = new TourDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		tourDao.close();
	}

	private Date lastUpdatedOn;
	
	@Test
	public void testUpdateTour() throws ParseException, IOException {
		Tour existTour = new Tour();
		existTour.setTourId(1);
		 
		Category someCategory = new Category("Shopping");
		category.setCategoryId(3); 
		existTour.setCategory(category);
		
		existTour.setExampleTitle("Kharkiv Palace Hotel");
		existTour.setCityCountry("Kharkiv, Ukraine");
		existTour.setDepiction("This 5-star hotel , which 30-minute drive from Kharkiv Airport.\\r\\n\" + \r\n" + 
				"\"\\r\\n\" + \r\n" + "\"All elegant rooms at Kharkiv Palace Hotel are decorated in a contemporary style. The Kharkiv Palace Hotel has several dining options, including the Terrace Restaurant with Terrace and the Pacific Spoon Restaurant. Drinks are available in the rooftop Sky Lounge with panoramic city views. Guests can also visit the swimming pool and work out at the fitness center and more.");
		existTour.setSomePrice(7000.00f);
		existTour.setSomeProductCode("3333333333");
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date somePublishDate = dateFormat.parse("03/28/2019");
		existTour.setPublishDate(publishDate);

		String imagePath = "C:/Users/Iryna/Project-wowtraveldb/WOWTravel/WebContent/images/KharkivPalaceHotel.jpg";
	
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		existTour.setImage(imageBytes);	

		Tour updatedTour = tourDao.update(existTour);
	
		assertEquals(updatedTour.getTitle(), "Kharkiv Palace Hotel");
	
	}	
	
	@Test
	public void testCreateTour() throws ParseException, IOException {
		Tour newTour = new Tour();
		Category someCategory = new Category("Excursion");
		category.setCategoryId(1);
		newTour.setCategory(category);

		newTour.setExampleTitle("Kharkiv Hotel");
		newTour.setCityCountry("Kharkiv, Ukraine");
		newTour.setDepiction("This is 4-star hotel, which provides direct access to Kharkiv International Airport (12 km).\\r\\n\" + \r\n" + 
			"\"\\r\\n\" + \r\n" + "\"After crossing the threshold of the Kharkiv Hotel, guests will find themselves in a large and spacious lobby. All rooms are decorated in a modern and classic style.");
		newTour.setSomePrice(7000.00f);
		newTour.setSomeProductCode("7737773773");
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date somePublishDate = dateFormat.parse("10/31/2020");
		newTour.setPublishDate(publishDate);

		String imagePath = "C:/Users/Iryna/Project-wowtraveldb/WOWTravel/WebContent/images/KharkivHotel.jpg";

		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newTour.setImage(imageBytes);	
			
		Tour createdTour = tourDao.create(newTour);

		assertTrue(createdTour.getTourId() > 0);

	}

	@Test
	public void testGetTourFail() {
		
		Integer tourId = 100;
		
		Tour tour = tourDao.get(tourId);
	
		assertNull(tour);
	}
		
	@Test
	public void testGetTourSuccess() {
		Integer tourId = 2;
		
		Tour tour = tourDao.get(tourId);
	
		assertNotNull(tour);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteTourFail() {
		Integer tourId = 100;
		
		tourDao.delete(toutId);
		
		assertFalse(false);
	}

	@Test
	public void testDeleteTourSuccess() {
		Integer tourId = 0;
		
		tourDao.delete(tourId);

		assertTrue(true);
	}

	@Test
	public void testListAll( ) {
		List<Tour> listTours = tourDao.listAll();
	
		for (Tour aTour : listTours) {
		
			System.out.println(aTour.getTitle() + " - " + aTour.getCityCountry());
		}
	
		assertFalse(listTours.isEmpty());
	}

	@Test
	public void testByTitleNotExist() {
		String someTitle = "KHARKIV PALACE HOTEL";
		
		Tour tour = tourDao.findByTitle(title);
	
		assertNull(tour);
	}

	@Test
	public void testByTitleExist() {
		String someTitle = "KHARKIV HOTEL";
		
		Tour tour = tourDao.findByTitle(title);
	
		System.out.println(tour.getCityCountry());
		
		System.out.println(tour.getPrice());
	
		assertNotNull(tour);
	}

	@Test
	public void testCount() {
		long totalTours = tourDao.count();

		assertEquals(2, totalTours);
	}

	@Test
	public void testListNewTours() {
		List<Tour> listNewTours = tourDao.listNewTours();
		
		for (Tour aTour : listNewTours) {
		
			System.out.println(aTour.getTitle() + " - " + aTour.getPublishDate());
		}
		
		assertEquals(1, listNewTours.size());
	}
	
	@Test
	public void testSearchTourInTitle() {
		String keyword = "Kharkiv Hotel";
		
		List<Tour> result = tourDao.search(keyword);
		
		for (Tour aTour : result) {
			System.out.println(aTour.getTitle());
		}
		
		assertEquals(1, result.size());
	}
	
	@Test
	public void testSearchTourInCityCountry() {
		String keyword = "Kharkiv, Ukraine";
		
		List<Tour> result = tourDao.search(keyword);
		
		for (Tour aTour : result) {
			System.out.println(aTour.getTitle());
		}
		
		assertEquals(1, result.size());
	}
	
	@Test
	public void testSearchTourInDepiction() {
		String keyword = "Located in the center of Kharkiv";
		
		List<Tour> result = tourDao.search(keyword);
		
		for (Tour aTour : result) {
			System.out.println(aTour.getTitle());
		}
		
		assertEquals(1, result.size());
	}	
	
	@Test
	public void testListByCategory() {
		int categoryId = 1;
		
		List<Tour> listTours = tourDao.listByCategory(categoryId);
		
		assertTrue(listTours.size() > 0);
	}
	
	@Test
	public void testListCountByCategory() {
		List<Tour> topBestSellingTours = tourDao.listBestSellingTours();
		
		for (Tour tour : topBestSellingTours) {
			System.out.println(tour.getTitle());
		}
		
		assertEquals(2, topBestSellingTours.size());
	}
	
	@Test
	public void testListMostFavoredTours() {
		List<Tour> topFavoredTours = tourDao.listMostFavoredTours();
		
		for (Tour tour : topFavoredTours) {
			System.out.println(tour.getTitle());
		}
		
		assertEquals(2, topFavoredTours.size());
	}

}
