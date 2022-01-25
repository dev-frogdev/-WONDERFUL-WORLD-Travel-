package com.wowtravel.controller.admin.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wowtravel.dao.TourDAO;
import com.wowtravel.entity.Tour;

@WebServlet("/admin/add_tour_form")
public class ShowAddTourFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ShowAddTourFormServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TourDAO tourDao = new TourDAO();
		List<Tour> listTour = tourDao.listAll();
		request.setAttribute("listTour", listTour);
		
		String addFormPage = "add_tour_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(addFormPage);
		dispatcher.forward(request, response);
	}

}
