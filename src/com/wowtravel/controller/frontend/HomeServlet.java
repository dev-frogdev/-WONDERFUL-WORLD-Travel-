package com.wowtravel.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wowtravel.controller.BaseServlet;
import com.wowtravel.dao.CategoryDAO;
import com.wowtravel.dao.TourDAO;
import com.wowtravel.entity.Category;
import com.wowtravel.entity.Tour;

@WebServlet("")
public class HomeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
    public HomeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TourDAO tourDAO = new TourDAO();
		
		List<Tour> listNewtours = tourDAO.listNewTours();
		List<Tour> listBestSellingTours = tourDAO.listBestSellingTours();
		List<Tour> listFavoredTours = tourDAO.listMostFavoredTours();
		
		request.setAttribute("listNewTours", listNewtours);
		request.setAttribute("listBestSellingTours", listBestSellingTours);
		request.setAttribute("listFavoredTours", listFavoredTours);
		
		String homepage = "frontend/index.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(homepage);
		dispatcher.forward(request, response);
	}

}
