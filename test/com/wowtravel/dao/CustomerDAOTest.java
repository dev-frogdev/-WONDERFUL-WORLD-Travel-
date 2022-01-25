package com.wowtravel.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wowtravel.entity.Customer;

public class CustomerDAOTest {
	private static CustomerDAO customerDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		customerDao = new CustomerDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		customerDao.close();
	}

	@Test
	public void testCreateCustomer() {
		Customer customer = new Customer();
		customer.setEmail("privet@java.ua");
		customer.setFullname("Iryna");
		customer.setPassportNumber("EA1111111");
		customer.setPassportCountry("Ukraine");
		customer.setDateOfBirth("07/07/1989");
		customer.setPassword("helloworld");
		customer.setPhoneNumber("0670000000");
		customer.setPassportDate("07/07/2007");
		customer.setPassportAuthority("3707");
		
		Customer savedCustomer = customerDao.create(customer);
		
		assertTrue(savedCustomer.getCustomerId() > 0);
	}

	@Test
	public void testGet() {
		Integer customerId = 2;
		Customer customer = customerDao.get(customerId);
		
		assertNotNull(customer);
	}

	@Test
	public void testUpdateCustomer() {
		Customer customer = customerDao.get(2);
		String fullname = "Iryna";
		customer.setFullname(fullname);
		Customer updatedCustomer = customerDao.update(customer);
		
		String email = "privet@java.ua";
		customer.setEmail(email);
		Customer updatedCustomerss = customerDao.update(customer);
		
		String password = "helloworld";
		customer.setPassword(password);		
		Customer updatedCustomers = customerDao.update(customer);
		
		String passportDate = "03/07/1989";
		customer.setPassportDate(passportDate);
		Customer updatedCustomerssss = customerDao.update(customer);
		
		String dateOfBirth = "03/07/1989";
		customer.setDateOfBirth(dateOfBirth);
		Customer updatedCustomersssssss = customerDao.update(customer);
		
		assertTrue(updatedCustomer.getFullname().equals(fullname));

		assertTrue(updatedCustomerss.getEmail().equals(email));
		
		assertTrue(updatedCustomersssssss.getDateOfBirth().equals(dateOfBirth));
		
		assertTrue(updatedCustomerssss.getPassportDate().equals(passportDate));
		
		assertTrue(updatedCustomers.getPassword().equals(password));
	}
	
	@Test
	public void testDeleteCustomer() {
		Integer customerId = 2;
		customerDao.delete(customerId);
		Customer customer = customerDao.get(2);
		
		assertNull(customer);
	}

	@Test
	public void testListAll() {
		List<Customer> listCustomers = customerDao.listAll();
		
		for (Customer customer : listCustomers) {
			System.out.println(customer.getFullname());
		}
	}
	
	@Test
	public void testCount() {
		long totalCustomers = customerDao.count();
		
		assertEquals(2, totalCustomers);
	}
	
	@Test
	public void testFindByEmail() {
		String email = "privet2@java.ua";
		Customer customer = customerDao.findByEmail(email);
		
		assertNotNull(customer);
		
	}
}
