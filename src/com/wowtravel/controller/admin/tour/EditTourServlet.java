package com.wowtravel.controller.admin.tour;

import com.wowtravel.service.TourServices;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/edit_tour")
public class EditTourServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditTourServlet() {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TourServices tourServices = new TourServices(request, response);
		tourServices.editTour();
	}

}
