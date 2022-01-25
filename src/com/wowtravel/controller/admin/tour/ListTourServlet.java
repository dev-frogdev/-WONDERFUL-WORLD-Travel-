package com.wowtravel.controller.admin.tour;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wowtravel.controller.BaseServlet;
import com.wowtravel.service.TourServices;

@WebServlet("/admin/list_tours")
public class ListTourServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 public ListTourServlet() {
	        super();
	    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TourServices tourServices = new TourServices(request, response);
		tourServices.listTours();
	}

}
