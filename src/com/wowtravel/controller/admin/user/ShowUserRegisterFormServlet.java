package com.wowtravel.controller.admin.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/reg")
public class ShowUserRegisterFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public ShowUserRegisterFormServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String regForm = "user_form.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(regForm);
		dispatcher.forward(request, response);
	}
}