package com.wowtravel.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.wowtravel.entity.OrderDetail;
import com.wowtravel.entity.TourOrder;

@Entity
@Table(name = "tour_order", catalog = "wowtraveldb")
@NamedQueries({
	@NamedQuery(name = "TourOrder.findAll", query = "SELECT bo FROM TourOrder bo ORDER BY bo.orderDate DESC"),
	@NamedQuery(name = "TourOrder.countAll", query = "SELECT COUNT(*) FROM TourOrder"),
	@NamedQuery(name = "TourOrder.findByCustomer", 
	query = "SELECT bo FROM TourOrder bo WHERE bo.customer.customerId =:customerId ORDER BY bo.orderDate DESC"),
	@NamedQuery(name = "TourOrder.findByIdAndCustomer", 
	query = "SELECT bo FROM TourOrder bo WHERE bo.orderId =:orderId AND bo.customer.customerId =:customerId")
})

public class TourOrder implements java.io.Serializable {

	private Integer orderId;
	private Customer customer;
	private Date orderDate;
	private String sendTo;
	private String fullname;
	private String phoneNumber;
	private String paymentMethod;
	private float orderTotal;
	private String orderStatus;
	private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>(0);

	public TourOrder() {
	}

	public TourOrder(Customer customer, Date orderDate, String sendTo, String fullname, String phoneNumber,
			String paymentMethod, float orderTotal, String orderStatus) {
		this.customer = customer;
		this.orderDate = orderDate;
		this.sendTo = sendTo;
		this.fullname = fullname;
		this.phoneNumber = phoneNumber;
		this.paymentMethod = paymentMethod;
		this.orderTotal = orderTotal;
		this.orderStatus = orderStatus;
	}

	public TourOrder(Customer customer, Date orderDate, String sendTo, String fullname, String phoneNumber,
			String paymentMethod, float orderTotal, String orderStatus, Set<OrderDetail> orderDetails) {
		this.customer = customer;
		this.orderDate = orderDate;
		this.sendTo = sendTo;
		this.fullname = fullname;
		this.phoneNumber = phoneNumber;
		this.paymentMethod = paymentMethod;
		this.orderTotal = orderTotal;
		this.orderStatus = orderStatus;
		this.orderDetails = orderDetails;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "order_id", unique = true, nullable = false)
	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", nullable = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "order_date", nullable = false, length = 19)
	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Column(name = "send_to", nullable = false, length = 256)
	public String getSendTo() {
		return this.sendTo;
	}

	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	@Column(name = "recipient_name", nullable = false, length = 30)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "recipient_phone", nullable = false, length = 15)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "payment_method", nullable = false, length = 20)
	public String getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Column(name = "order_total", nullable = false, precision = 12, scale = 0)
	public float getOrderTotal() {
		return this.orderTotal;
	}

	public void setOrderTotal(float orderTotal) {
		this.orderTotal = orderTotal;
	}

	@Column(name = "order_status", nullable = false, length = 20)
	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tourOrder", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<OrderDetail> getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	
	@Transient
	public int getTourCopies() {
		int orderTotal = 0;
		
		for (OrderDetail orderDetail : orderDetails) {
			orderTotal += orderDetail.getQuantity();
		}
		return orderTotal;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
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
		TourOrder other = (TourOrder) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		return true;
	}

}
