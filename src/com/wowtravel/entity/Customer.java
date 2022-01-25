package com.wowtravel.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "customer", catalog = "wowtraveldb", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@NamedQueries({
	@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c ORDER BY c.registerOn DESC"),
	@NamedQuery(name = "Customer.findByEmail", query = "SELECT c FROM Customer c WHERE c.email = :email"),
	@NamedQuery(name = "Customer.countAll", query = "SELECT COUNT(c.email) FROM Customer c"),
	@NamedQuery(name = "Customer.checkLogin", query = "SELECT c FROM Customer c WHERE c.email = :email AND c.password = :pass")
})
public class Customer implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer customerId;
	private String email;
	private String fullname;
	private String dateOfBirth;
	private String passportNumber;
	private String passportCountry;
	private String phoneNumber;
	private String passportDate;
	private String password;
	private Date registerOn;
	private String passportAuthority;
	private Set<Review> reviews = new HashSet<Review>(0);
	private Set<TourOrder> tourOrders = new HashSet<TourOrder>(0);

	public Customer() {
	}

	public Customer(String email, String fullname, String dateOfBirth, String passportNumber, String passportCountry,
			String phoneNumber, String passportDate, String password, Date registerOn, String passportAuthority) {
		this.email = email;
		this.fullname = fullname;
		this.dateOfBirth = dateOfBirth;
		this.passportNumber = passportNumber;
		this.passportCountry = passportCountry;
		this.phoneNumber = phoneNumber;
		this.passportDate = passportDate;
		this.password = password;
		this.registerOn = registerOn;
		this.passportAuthority = passportAuthority;
	}

	public Customer(String email, String fullname, String dateOfBirth, String passportNumber, String passportCountry,
			String phoneNumber, String passportDate, String password, Date registerOn, String passportAuthority,
			Set<Review> reviews, Set<TourOrder> tourOrders) {
		this.email = email;
		this.fullname = fullname;
		this.dateOfBirth = dateOfBirth;
		this.passportNumber = passportNumber;
		this.passportCountry = passportCountry;
		this.phoneNumber = phoneNumber;
		this.passportDate = passportDate;
		this.password = password;
		this.registerOn = registerOn;
		this.passportAuthority = passportAuthority;
		this.reviews = reviews;
		this.tourOrders = tourOrders;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "customer_id", unique = true, nullable = false)
	public Integer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	@Column(name = "email", unique = true, nullable = false)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "full_name", nullable = false, length = 30)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "date_of_birth", nullable = false, length = 128)
	public String getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "passport_number", nullable = false, length = 32)
	public String getPassportNumber() {
		return this.passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	@Column(name = "passport_country", nullable = false, length = 64)
	public String getPassportCountry() {
		return this.passportCountry;
	}

	public void setPassportCountry(String passportCountry) {
		this.passportCountry = passportCountry;
	}

	@Column(name = "phone_number", nullable = false, length = 15)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "passport_date", nullable = false, length = 24)
	public String getPassportDate() {
		return this.passportDate;
	}

	public void setPassportDate(String passportDate) {
		this.passportDate = passportDate;
	}

	@Column(name = "password", nullable = false, length = 16)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "register_on", nullable = false, length = 19)
	public Date getRegisterOn() {
		return this.registerOn;
	}

	public void setRegisterOn(Date registerOn) {
		this.registerOn = registerOn;
	}

	@Column(name = "passport_authority", nullable = false)
	public String getPassportAuthority() {
		return this.passportAuthority;
	}

	public void setPassportAuthority(String passportAuthority) {
		this.passportAuthority = passportAuthority;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
	public Set<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
	public Set<TourOrder> getTourOrders() {
		return this.tourOrders;
	}

	public void setTourOrders(Set<TourOrder> tourOrders) {
		this.tourOrders = tourOrders;
	}

}
