package com.wowtravel.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.wowtravel.entity.OrderDetailId;
import com.wowtravel.entity.Tour;
import com.wowtravel.entity.TourOrder;

@Entity
@Table(name = "order_detail", catalog = "wowtraveldb")
@NamedQueries({
@NamedQuery(name = "OrderDetail.bestSelling",
		query = "SELECT od.tour FROM OrderDetail od GROUP by od.tour.tourId "
		+ "ORDER BY SUM(od.quantity) DESC")
})
public class OrderDetail implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private OrderDetailId id = new OrderDetailId();
	private Tour tour;
	private TourOrder tourOrder;
	private int quantity;
	private float subtotal;

	public OrderDetail() {
	}

	public OrderDetail(OrderDetailId id) {
		this.id = id;
	}

	public OrderDetail(OrderDetailId id, Tour tour, TourOrder tourOrder, int quantity, float subtotal) {
		this.id = id;
		this.tour = tour;
		this.tourOrder = tourOrder;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "orderId", column = @Column(name = "order_id")),
			@AttributeOverride(name = "tourId", column = @Column(name = "tour_id"))})
		
	public OrderDetailId getId() {
		return this.id;
	}

	public void setId(OrderDetailId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tour_id", insertable = false, updatable = false, nullable = false)
	public Tour getTour() {
		return this.tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
		this.id.setTour(tour);
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", insertable = false, updatable = false, nullable = false)
	public TourOrder getTourOrder() {
		return this.tourOrder;
	}

	public void setTourOrder(TourOrder tourOrder) {
		this.tourOrder = tourOrder;
		this.id.setTourOrder(tourOrder);
	}
	
	@Column(name = "quantity_of_people", nullable = false)
	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "subtotal", nullable = false, precision = 12, scale = 0)
	public float getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
}


