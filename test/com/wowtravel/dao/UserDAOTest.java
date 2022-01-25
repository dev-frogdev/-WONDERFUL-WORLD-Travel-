package com.wowtravel.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wowtravel.entity.Users;

public class UserDAOTest {
	private static UserDAO userDAO;
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		userDAO = new UserDAO();
		
	}
	@Test
	public void testCreateUsers() {
		Users user1 = new Users();
		user1.setEmail("privet@java.ua");
		user1.setFullName("Iryna");
		user1.setPassword("helloworld");
		
		user1 = userDAO.create(user1);
		
		assertTrue(user1.getUserId() > 0);
		
	}

	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldsNotSet() {
		Users user1 = new Users();		
		user1 = userDAO.create(user1);
				
	}

	@Test
	public void testUpdateUsers() {
		Users user = new Users();
		user.setUserId(70);
		user.setEmail("privet@java.ua");
		user.setFullName("Iryna");
		user.setPassword("helloworld1");
		
		user = userDAO.update(user);
		String expected = "helloworld1";
		String actual = user.getPassword();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetUsersFound() {
		Integer userId = 1;
		Users user = userDAO.get(userId);
		if (user !=null) {
			System.out.println(user.getEmail());
		}
		System.out.println(user.getEmail());
		assertNotNull(user);
	}
	
	@Test
	public void testGetUsersNotFound() {
		Integer userId = 99;
		Users user = userDAO.get(userId);
		
		assertNull(user);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteNotExistUsers() {
		Integer userId = 1;
		userDAO.delete(userId);
	}
	
	@Test
	public void testListAll() {
		List<Users> listUsers = userDAO.listAll();
		
		for (Users user : listUsers) {
			System.out.println(user.getEmail());
		
		}
		assertTrue(listUsers.size() > 0);
	}
	
	@Test
	public void testCount() {
		long totalUsers = userDAO.count();
		assertTrue(totalUsers > 0);
	}
	
	@Test
	public void testCheckLoginSuccess() {
		String email = "privet@java.ua";
		String password = "helloworld";
		boolean loginResult = userDAO.checkLogin(email, password);
		
		assertTrue(loginResult);
	}
	
	@Test
	public void testCheckLoginFailed() {
		String email = "privet7@java.ua";
		String password = "helloworld";
		boolean loginResult = userDAO.checkLogin(email, password);
		
		assertFalse(loginResult);
	}
	
	@Test
	public void testFindByEmail() {
		String email = "privet@java.ua";
		Users user = userDAO.findByEmail(email);
		
		assertNotNull(user);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	userDAO.close();
	}
}
