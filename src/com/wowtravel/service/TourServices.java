package com.wowtravel.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.wowtravel.dao.CategoryDAO;
import com.wowtravel.dao.TourDAO;
import com.wowtravel.entity.Category;
import com.wowtravel.entity.Tour;

public class TourServices {
	private TourDAO tourDAO;
	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Integer categoryId;
	
	
	
	public TourServices(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		tourDAO = new TourDAO();
		categoryDAO = new CategoryDAO();
	}


	public void listTours() throws ServletException, IOException{
		listTours(null);
	}
	
	public void listTours(String message) throws ServletException, IOException {
		List<Tour> listTours = tourDAO.listAll();
		request.setAttribute("listTours", listTours);
		
		if (message != null) {
			request.setAttribute("message", message);
		}
		String listPage = "tour_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
		
	}
	
	public void showTourNewForm() throws ServletException, IOException {
		List<Category> listCategory = categoryDAO.listAll();
		request.setAttribute("listCategory", listCategory);
		
		String newPage = "tour_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(newPage);
		requestDispatcher.forward(request, response);
	}
	
	public void createTour() throws ServletException, IOException {
	categoryId = Integer.parseInt(request.getParameter("category"));
	String title = request.getParameter("title");
	
	Tour existTour = tourDAO.findByTitle(title);
	if (existTour != null) {
		String message = "Could not create new because the title '" + title + "'already exists.";	
		listTours(message);
		return;
	}
	
	Tour newTour = new Tour();
	readTourFields(newTour);
	
	Tour createdTour = tourDAO.create(newTour);
	
	if (createdTour.getTourId() > 0) {
	String message = "A new tour has been created successfully.";
	listTours(message);
	}
	}

public void readTourFields(Tour tour) throws ServletException, IOException {
	String title = request.getParameter("title");
	String cityCountry = request.getParameter("cityCountry");
	String depiction = request.getParameter("depiction");
	String productCode = request.getParameter("productCode");
	float price = Float.parseFloat(request.getParameter("price"));
	
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	Date publishDate = null;
	
	try{
		publishDate = dateFormat.parse(request.getParameter("publishDate"));
	} catch (ParseException ex) {
		ex.printStackTrace();
		throw new ServletException("Error parsing publish date (format is MM/dd/yyyy)");
	}
	
	tour.setTitle(title);
	tour.setCityCountry(cityCountry);
	tour.setDepiction(depiction);
	tour.setProductCode(productCode);
	tour.setPublishDate(publishDate);
	
	Integer categoryId = Integer.parseInt(request.getParameter("category"));
	Category category = categoryDAO.get(categoryId);
	tour.setCategory(category);
	
	tour.setPrice(price);
	
	Part part = request.getPart("tourImage");
	
	if (part !=null && part.getSize() > 0) {
		long size = part.getSize();
	byte[] imageBytes = new byte[(int) size];
	
	InputStream inputStream = part.getInputStream();
	inputStream.read(imageBytes);
	inputStream.close();
	
	tour.setImage(imageBytes);
	
	}
	
	
}
	public void editTour() throws ServletException, IOException {
		Integer tourId = Integer.parseInt(request.getParameter("id"));
		Tour tour = tourDAO.get(tourId);
		List<Category> listCategory = categoryDAO.listAll();
		
		request.setAttribute("tour", tour);
		request.setAttribute("listCategory", listCategory);
		
		String editPage = "tour_form.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}


	public void updateTour() throws ServletException, IOException {
		
		Integer tourId = Integer.parseInt(request.getParameter("tourId"));
		String title = request.getParameter("title");
		
		Tour existTour = tourDAO.get(tourId);
		Tour tourByTitle = tourDAO.findByTitle(title);
		
		if (tourByTitle !=null && !existTour.equals(tourByTitle)) {
			String message = "Could  not update tour because there's another tour having same title.";
			listTours(message);
			return;
		}
		
		readTourFields(existTour);
		
		tourDAO.update(existTour);
		
		String message = "The tour has been updated successfully.";
		listTours(message);
		
	}


	public void deleteTour() throws ServletException, IOException {
		Integer tourId = Integer.parseInt(request.getParameter("id"));
				
		tourDAO.delete(tourId);
		
		String message = "The tour has been deleted successfully.";
		listTours(message);
	}


	public void listToursByCategory() throws ServletException, IOException {
			int categoryId = Integer.parseInt(request.getParameter("id"));	
			List<Tour> listTours = tourDAO.listByCategory(categoryId);
			Category category = categoryDAO.get(categoryId);
			List<Category> listCategory = categoryDAO.listAll();
			
			request.setAttribute("listategory", listCategory);
			request.setAttribute("listTours", listTours);
			request.setAttribute("category", category);
			
			String listPage = "frontend/tours_list_by_category.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
			requestDispatcher.forward(request, response);
	}


	public void viewTourDetail() throws ServletException, IOException {
		Integer tourId = Integer.parseInt(request.getParameter("id"));
		Tour tour = tourDAO.get(tourId);
		List<Category> listCategory = categoryDAO.listAll();
				
		request.setAttribute("listategory", listCategory);
		request.setAttribute("tour", tour);
		
		String detailPage = "frontend/tour_detail.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(detailPage);
		requestDispatcher.forward(request, response);
		}


	public void search() throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		List<Tour> result = null;
		
		if (keyword.equals("")) {
			result = tourDAO.listAll();
		} else {
			result = tourDAO.search(keyword);
		}
		
		request.setAttribute("keyword", keyword);
		request.setAttribute("result", result);
		
		String resultPage = "frontend/search_result.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(resultPage);
		requestDispatcher.forward(request, response);
	}
}
	
