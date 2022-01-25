package com.wowtravel.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.wowtravel.entity.OrderDetailId;
import com.wowtravel.entity.Tour;
import com.wowtravel.entity.TourOrder;

@Embeddable
public class OrderDetailId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Tour tour;
	private TourOrder tourOrder;
	
	public OrderDetailId() {
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tour_id", insertable = false, updatable = false, nullable = false)
	public Tour getTour() {
		return this.tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "order_id", insertable = false, updatable = false, nullable = false)
	public TourOrder getTourOrder() {
		return this.tourOrder;
	}

	public void setTourOrder(TourOrder tourOrder) {
		this.tourOrder = tourOrder;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tour == null) ? 0 : tour.hashCode());
		result = prime * result + ((tourOrder == null) ? 0 : tourOrder.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetailId other = (OrderDetailId) obj;
		if (tour == null) {
			if (other.tour != null)
				return false;
		} else if (!tour.equals(other.tour))
			return false;
		if (tourOrder == null) {
			if (other.tourOrder != null)
				return false;
		} else if (!tourOrder.equals(other.tourOrder))
			return false;
		return true;
	}	
	
}
