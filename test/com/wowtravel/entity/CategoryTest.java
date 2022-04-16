package com.wowtravel.entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.wowtravel.entity.Category;

public class CategoryTest {

	public static void main(String[] args) {
		Category newCategory = new Category("Shopping");
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("WOWTravel");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();

		entityManager.persist(newCategory);

		entityManager.getTransaction().commit();
		
		entityManager.close();
		
		entityManagerFactory.close();
		
		System.out.println("A Type object was persisted");

	}

}
