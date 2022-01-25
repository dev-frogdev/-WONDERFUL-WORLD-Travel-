package com.wowtravel.controller.admin.tour;

import com.wowtravel.service.TourServices;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/create_tour")
@MultipartConfig(
		fileSizeThreshold = 1024 * 10, //10 КВ
		maxFileSize = 1024 * 300, //300 КВ
		maxRequestSize = 1024 *  1024 //1 МВ
)
public class CreateTourServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CreateTourServlet() { 
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	TourServices tourServices = new TourServices(request, response);
	tourServices.createTour();
	}

}
