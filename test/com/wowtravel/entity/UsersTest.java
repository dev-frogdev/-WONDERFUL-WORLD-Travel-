package com.wowtravel.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.wowtravel.entity.Users;

public class UsersTest {


	public static void main(String[] args) {
		Users user1 = new Users();
		user1.setEmail("privet@java.ua");
		user1.setFullName("Iryna");
		user1.setPassword("helloworld");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("WOWTravel");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();

		entityManager.persist(user1);

		entityManager.getTransaction().commit();
		
		entityManager.close();
		
		entityManagerFactory.close();
		
		System.out.println("A Users object was persisted");

	}

}