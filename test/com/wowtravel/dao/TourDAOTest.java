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
		existTour.setTourId(49);
		 
		Category category = new Category("Shopping");
		category.setCategoryId(3); 
		existTour.setCategory(category);
		
		existTour.setTitle("For example, Armani Hotel Dubai");
		existTour.setCityCountry("Dubai, United Arab Emirates");
		existTour.setDepiction("Envisioned by fashion icon Giorgio Armani, this hotel has a private entrance and occupies 11 floors of Dubai’s iconic skyscraper, Burj Khalifa. It has a deluxe spa and has direct access to Dubai Mall.\\r\\n\" + \r\n" + 
				"				\"\\r\\n\" + \r\n" + 
				"				\"Rooms of understated elegance feature curved lines are decorated with Japanese tatami and luxury fabrics. Modern appliances include flat-screen TVs with DVD, iPod docking stations and free WiFi.\\r\\n\" + \r\n" + 
				"				\"\\r\\n\" + \r\n" + 
				"				\"Armani Hotel Dubai provides sophisticated dining options in each of its 7 restaurants. Armani Hashi serves Japanese cuisine with a modern twist and renowned Armani Privé hosts the most popular club nights in town.\\r\\n\" + \r\n" + 
				"				\"\\r\\n\" + \r\n" + 
				"				\"Guests can relax at the extensive Armani Spa, offering personal treatments and sequential bathing. Dubai International Airport is a 20-minute drive from the property.");
		existTour.setPrice(7000.00f);
		existTour.setProductCode("3333333333");
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("03/28/2019");
		existTour.setPublishDate(publishDate);
	
        //All images size reduced to 350x231 pixels
	String imagePath = "C:/Users/Iryna/Project-wowtraveldb/WOWTravel/WebContent/images/ArmaniHotelDubaiDubaisize.jpg";
	
	byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
	existTour.setImage(imageBytes);	

	Tour updatedTour = tourDao.update(existTour);
	
	assertEquals(updatedTour.getTitle(), "For example, Armani Hotel Dubai");
	
	}	
	
	@Test
	public void testCreateTour() throws ParseException, IOException {
		Tour newTour = new Tour();
		Category category = new Category("Excursion");
		category.setCategoryId(1);
		newTour.setCategory(category);

		newTour.setTitle("For example, Sheraton Grand Hotel & Spa");
		newTour.setCityCountry("Edinburgh United Kingdom");
		newTour.setDepiction("Overlooking Edinburgh Castle, the Sheraton Grand Hotel & Spa is located in the financial district, next to the West End business and entertainment area and a five-minute walk from Princes Street, 500 m from Edinburgh Castle.\r\n" + 
				"\r\n" + 
				"The luxurious, elegant rooms provide free wireless Internet access, 24-hour room service, interactive satellite flat-screen TV, small sitting areas, and bathrooms with a separate bath and walk-in shower. The large air-conditioned rooms have beautiful furnishings and mood lighting.\r\n" + 
				"\r\n" + 
				"At One Spa, guests can enjoy complimentary use of the 19 m swimming pool, gym and fitness studios, and can indulge in a variety of beauty and spa treatments during their stay (must be booked prior to arrival). The unique and recently renovated Thermal Suite and rooftop Hydropool experiences, known as Escape at One, can be enjoyed at a preferential rate. These should be booked in advance prior arrival at the hotel, together with the Thermal Suite and Rooftop Hydro Pool, which are charged separately.\r\n" + 
				"\r\n" + 
				"A modern Scottish menu and unique Gin Tasting experience is available in the vibrant One Square Bar + Brasserie.");
		newTour.setPrice(7000.00f);
		newTour.setProductCode("7737773773");
		

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("10/31/2020");
		newTour.setPublishDate(publishDate);

		String imagePath = "C:/Users/Iryna/Project-wowtraveldb/WOWTravel/WebContent/images/SheratonGrandHotelEdynburgsize.jpg";

		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newTour.setImage(imageBytes);	
			
		Tour createdTour = tourDao.create(newTour);

		assertTrue(createdTour.getTourId() > 0);

	}


	@Test
	public void testGetTourFail() {
	Integer tourId = 99;
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
Integer toutId = 100;
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

//@Test
//public void testCreate2ndTour() throws ParseException, IOException {
//	Tour newTour = new Tour();
//	Category category = new Category("Shopping");
//	category.setCategoryId(2);
//	newTour.setCategory(category);
	
//	newTour.setTitle("For example, GRAND HOTELS");
//	newTour.setCityCountry("New York");
//	newTour.setDescription("Beautiful");
//	newTour.setPrice(2000.00f);
//	newTour.setProductCode("1111111112");
	
//	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//Date publishDate = dateFormat.parse("02/07/2019");
//newTour.setPublishDate(publishDate);

//String imagePath = "D:\\images\\rest\\IberostarDominicana.jpg";

//byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
//	newTour.setImage(imageBytes);	

//Tour createdTour = tourDao.create(newTour);

//assertTrue(createdTour.getTourId() > 0);

//}

@Test
public void testByTitleNotExist() {
	String title = "For example, IBEROSTAR";
	Tour tour = tourDao.findByTitle(title);
	
	assertNull(tour);
}

@Test
public void testByTitleExist() {
	String title = "For example, IIBEROSTAR";
	Tour tour = tourDao.findByTitle(title);
	
	System.out.println(tour.getCityCountry());
	System.out.println(tour.getPrice());
	
	assertNotNull(tour);
}

	@Test
	public void testCount() {
		long totalTours = tourDao.count();

		assertEquals(4, totalTours);
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
		String keyword = "For example, Le Meridien Vienna";
		List<Tour> result = tourDao.search(keyword);
		
		for (Tour aTour : result) {
			System.out.println(aTour.getTitle());
		}
		
		assertEquals(1, result.size());
	}
	
	@Test
	public void testSearchTourInCityCountry() {
		String keyword = "Vienna, Austria";
		List<Tour> result = tourDao.search(keyword);
		
		for (Tour aTour : result) {
			System.out.println(aTour.getTitle());
		}
		
		assertEquals(1, result.size());
	}
	
	@Test
	public void testSearchTourInDepiction() {
		String keyword = "Located right on the Ringstrasse in the heart of Vienna";
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
