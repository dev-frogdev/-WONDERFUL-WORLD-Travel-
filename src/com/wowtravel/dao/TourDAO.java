package com.wowtravel.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.wowtravel.entity.Category;
import com.wowtravel.entity.Tour;

public class TourDAO extends JpaDAO<Tour> implements GenericDAO<Tour> {

	public TourDAO() {
		
			}

	@Override
	public Tour create(Tour tour) {
	tour.setLastUpdatedOn(new Date());
	return super.create(tour);
	}

	@Override
	public Tour update(Tour tour) {
	tour.setLastUpdatedOn(new Date());
	return super.update(tour);
	}

	@Override
	public Tour get(Object tourId) {
		return super.find(Tour.class, tourId);
	}

	@Override
	public void delete(Object tourId) {
		super.delete(Tour.class,  tourId);
		
	}

		
	@Override
	public List<Tour> listAll() {
		return super.findWithNamedQuery("Tour.findAll");
	}
		
		public Tour findByTitle(String title) {
		List<Tour> result = super.findWithNamedQuery("Tour.findByTitle", "title", title);
		
		if  (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
		}
		
		public List<Tour> listByCategory(int categoryId) {
		return super.findWithNamedQuery("Tour.findByCategory", "catId", categoryId);
		}
		
		public List<Tour> search(String keyword) {
			return super.findWithNamedQuery("Tour.search", "keyword", keyword);
		}
		
		public List<Tour> listNewTours() {
			return super.findWithNamedQuery("Tour.listNew", 0, 4);
		}
		
	@Override
	public long count() {
		return super.countWithNamedQuery("Tour.countAll");
	}
	
//	public long countByCategory(int categoryId) {
//		return super.countWithNamedQuery("Tour.countByCategory", "catId", categoryId);
//	}
	
	public List<Tour> listBestSellingTours() {
		return super.findWithNamedQuery("OrderDetail.bestSelling", 0, 4);
	}
	
	public List<Tour> listMostFavoredTours() {
		List<Tour> mostFavoredTours = new ArrayList<>();
		
		List<Object[]> result = super.findWithNamedQueryObjects("Review.mostFavoredTours", 0, 4);
		
		if (!result.isEmpty()) {
			for (Object[] elements : result) {
				Tour tour = (Tour) elements[0];
				mostFavoredTours.add(tour);
			}
		}
		
		return mostFavoredTours;
	}

	public Tour findByName(String tourName) {
		List<Tour> result = super.findWithNamedQuery("Tour.findByName", "name", tourName);
		
		if (result !=null && result.size() > 0) {
			
		return result.get(0);
		}
		
		return null;
	}

}
