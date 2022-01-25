package com.wowtravel.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wowtravel.dao.CustomerDAO;
import com.wowtravel.entity.Customer;

public class CustomerServices {
private CustomerDAO customerDAO;
private HttpServletRequest request;
private HttpServletResponse response;


public CustomerServices(HttpServletRequest request, HttpServletResponse response) {
	super();
	this.request = request;
	this.response = response;
	this.customerDAO = new CustomerDAO();
}

public void listCustomers(String message) throws ServletException, IOException {
		List<Customer> listCustomer = customerDAO.listAll();
		
		if (message != null) {
			request.setAttribute("message", message);
		}
		request.setAttribute("listCustomer", listCustomer);
		String listPage = "customer_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
		requestDispatcher.forward(request, response);
}

public void listCustomers() throws ServletException, IOException {
		listCustomers(null);
}

public void createCustomer() throws ServletException, IOException {
	String email = request.getParameter("email");
	Customer existCustomer = customerDAO.findByEmail(email);
	String message = "";		
	
	if (existCustomer !=null) {
		message = "Could not create new customer: the email "
				+ email + " is already registered by another customer.";
		listCustomers(message);
	}else {
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
		String phoneNumber = request.getParameter("phoneNumber");
		String dateOfBirth = request.getParameter("dateOfBirth");
		String passportNumber = request.getParameter("passportNumber");
		String passportDate = request.getParameter("passportDate");
		String passportCountry = request.getParameter("passportCountry");
		String passportAuthority = request.getParameter("passportAuthority");
		
		
		Customer newCustomer = new Customer();
		newCustomer.setEmail(email);
		newCustomer.setFullname(fullname);
		newCustomer.setPassword(password);
		newCustomer.setPhoneNumber(phoneNumber);
		newCustomer.setDateOfBirth(dateOfBirth);
		newCustomer.setPassportNumber(passportNumber);
		newCustomer.setPassportDate(passportDate);
		newCustomer.setPassportCountry(passportCountry);
		newCustomer.setPassportAuthority(passportAuthority);
		
		customerDAO.create(newCustomer);
		
		message =  "New customer has been created successfully.";
		listCustomers(message);
	}
}

public void registerCustomer() throws ServletException, IOException {
	String email = request.getParameter("email");
	Customer existCustomer = customerDAO.findByEmail(email);
	String message = "";			
	
	if (existCustomer !=null) {
		message = "Could not register. The email "
				+ email + " is already registered by another customer.";
	}else {
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
		String phoneNumber = request.getParameter("phoneNumber");
		String dateOfBirth = request.getParameter("dateOfBirth");
		String passportNumber = request.getParameter("passportNumber");
		String passportDate = request.getParameter("passportDate");
		String passportCountry = request.getParameter("passportCountry");
		String passportAuthority = request.getParameter("passportAuthority");
		
		
		Customer newCustomer = new Customer();
		newCustomer.setEmail(email);
		newCustomer.setFullname(fullname);
		newCustomer.setPassword(password);
		newCustomer.setPhoneNumber(phoneNumber);
		newCustomer.setDateOfBirth(dateOfBirth);
		newCustomer.setPassportNumber(passportNumber);
		newCustomer.setPassportDate(passportDate);
		newCustomer.setPassportCountry(passportCountry);
		newCustomer.setPassportAuthority(passportAuthority);
		
		customerDAO.create(newCustomer);
		
		message =  "You have registered successfully! Thank you.<br/>"
				+ "<a href='login'>Click here</a> to login";
		
		
		listCustomers(message);
	}
	
	String messagePage = "frontend/message.jsp";
	RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
	request.setAttribute("message", message);
	requestDispatcher.forward(request, response);
}

public void editCustomer() throws ServletException, IOException {
	Integer customerId = Integer.parseInt(request.getParameter("id"));
	Customer customer = customerDAO.get(customerId);
	
	request.setAttribute("customer", customer);
	
	String editPage = "customer_form.jsp";
	RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
	requestDispatcher.forward(request, response);
	
}

public void updateCustomer() throws ServletException, IOException {
	Integer customerId = Integer.parseInt(request.getParameter("customerId"));
	String email = request.getParameter("email");
	
	Customer customerByEmail = customerDAO.findByEmail(email);
	String message = null;
	
	if (customerByEmail != null && customerByEmail.getCustomerId() != customerId) {
		message = "Could not update the customer ID " + customerId 
				+ "because there's an existing customer having the same email.";
	} else {
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
		String phoneNumber = request.getParameter("phoneNumber");
		String dateOfBirth = request.getParameter("dateOfBirth");
		String passportNumber = request.getParameter("passportNumber");
		String passportDate = request.getParameter("passportDate");
		String passportCountry = request.getParameter("passportCountry");
		String passportAuthority = request.getParameter("passportAuthority");
				
		Customer customerById = customerDAO.get(customerId);
		customerById.setEmail(email);
		customerById.setFullname(fullname);
		customerById.setPassword(password);
		customerById.setPhoneNumber(phoneNumber);
		customerById.setDateOfBirth(dateOfBirth);
		customerById.setPassportNumber(passportNumber);
		customerById.setPassportDate(passportDate);
		customerById.setPassportCountry(passportCountry);
		customerById.setPassportAuthority(passportAuthority);
		
		customerDAO.update(customerById);
		
		message = "The customer has been updated successfully.";
		}
	listCustomers(message);
	}

public void deleteCustomer() throws ServletException, IOException {
	Integer customerId = Integer.parseInt(request.getParameter("id"));
	customerDAO.delete(customerId);
	
	String message = "The customer has been deleted successfully.";
	listCustomers(message);
}

public void showLogin() throws ServletException, IOException {
	String loginPage = "frontend/login.jsp";
	RequestDispatcher dispatcher = request.getRequestDispatcher(loginPage);
	dispatcher.forward(request, response);
}

public void doLogin() throws ServletException, IOException {
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	 
	Customer customer = customerDAO.checkLogin(email, password);
	
	if (customer == null) {
		String message = "Login failed. Please check your email and password.";
		request.setAttribute("message", message);
		showLogin();
	} else {
		HttpSession session = request.getSession();
		request.getSession().setAttribute("loggedCustomer", customer);
		
		Object objRedirectURL = session.getAttribute("redirectURL");
		
		if (objRedirectURL != null) {
			String redirectURL = (String) objRedirectURL;
			session.removeAttribute("redirectURL");
			response.sendRedirect(redirectURL);
		} else {
		showCustomerProfile();
		}
	}
}

public void showCustomerProfile() throws ServletException, IOException {
	String profilePage = "frontend/customer_profile.jsp";
	RequestDispatcher dispatcher = request.getRequestDispatcher(profilePage);
	dispatcher.forward(request, response);
}

public void showCustomerProfileEditForm() throws ServletException, IOException {
	String editPage = "frontend/edit_profile.jsp";
	RequestDispatcher dispatcher = request.getRequestDispatcher(editPage);
	dispatcher.forward(request, response);
}

public void updateCustomerProfile() throws ServletException, IOException {
	Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
	updateCustomerProfileFromForm(customer);
	customerDAO.update(customer);
	
	showCustomerProfile();
}

private void updateCustomerProfileFromForm(Customer customer) {
	String email = request.getParameter("email");
	String fullname = request.getParameter("fullname");
	String password = request.getParameter("password");
	String phoneNumber = request.getParameter("phoneNumber");
	String dateOfBirth = request.getParameter("dateOfBirth");
	String passportNumber = request.getParameter("passportNumber");
	String passportDate = request.getParameter("passportDate");
	String passportCountry = request.getParameter("passportCountry");
	String passportAuthority = request.getParameter("passportAuthority");
	
	if (email != null && !email.equals("")) {
	customer.setEmail(email);
	}
	
	customer.setFullname(fullname);
	
	if (password != null && !password.equals("")) {
	customer.setPassword(password);
	}
	
	customer.setPhoneNumber(phoneNumber);
	customer.setDateOfBirth(dateOfBirth);
	customer.setPassportNumber(passportNumber);
	customer.setPassportDate(passportDate);
	customer.setPassportCountry(passportCountry);
	customer.setPassportAuthority(passportAuthority);
}
}
