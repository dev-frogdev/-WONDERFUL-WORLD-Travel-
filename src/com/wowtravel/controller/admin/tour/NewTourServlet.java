package com.wowtravel.controller.admin.tour;

import com.wowtravel.controller.BaseServlet;
import com.wowtravel.service.TourServices;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/new_tour")
public class NewTourServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
    public NewTourServlet() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TourServices tourServices = new TourServices(request, response);
		tourServices.showTourNewForm();
	}

}
