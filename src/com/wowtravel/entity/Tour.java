package com.wowtravel.entity;

import java.util.Base64;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tour", catalog = "wowtraveldb", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
@NamedQueries({
	@NamedQuery(name = "Tour.findAll", query = "SELECT b FROM Tour b"),
	@NamedQuery(name = "Tour.findByTitle", query = "SELECT b FROM Tour b WHERE b.title = :title"),
	@NamedQuery(name = "Tour.countAll", query = "SELECT COUNT(*) FROM Tour b"),
	@NamedQuery(name = "Tour.findByCategory", query = "SELECT b FROM Tour b JOIN Category c ON b.category.categoryId = c.categoryId AND c.categoryId = :catId"),
	@NamedQuery(name="Tour.listNew", query = "SELECT b FROM Tour b ORDER BY b.publishDate DESC"),
	@NamedQuery(name = "Tour.search", query = "SELECT b FROM Tour b WHERE b.title LIKE '%' || :keyword || '%'"
	+ " OR b.cityCountry LIKE '%' || :keyword || '%'"
	+ " OR b.depiction LIKE '%' || :keyword || '%'")
})
public class Tour implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer tourId;
	private Category category;
	private String title;
	private String cityCountry;
	private String depiction;
	private String productCode;
	private byte[] image;
	private String baseImage;
	private float price;
	private Date publishDate;
	private Date lastUpdatedOn;
	private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>(0);
	private Set<Review> reviews = new HashSet<Review>(0);

	public Tour(Category category, String title, String cityCountry, String depiction, String productCode, byte[] image, float price, Date publishDate, Date lastUpdatedOn) {

				
		 
		this.category = category;
		this.title = title;
		this.cityCountry = cityCountry;
		this.depiction = depiction;
		this.productCode = productCode;
		this.image = image;
		this.price = price;	
		this.publishDate = publishDate;
		this.lastUpdatedOn = lastUpdatedOn;
		
	}

	public Tour(Category category, String title, String cityCountry, String depiction, byte[] image, float price, Date publishDate, Date lastUpdatedOn,
			 Set<OrderDetail> orderDetails,  Set<Review> reviews, String productCode) {
	  
		
		this.category = category;
		this.title = title;
		this.cityCountry = cityCountry;
		this.depiction = depiction;
		this.productCode = productCode;
		this.image = image;
		this.price = price;
		this.publishDate = publishDate;
		this.lastUpdatedOn = lastUpdatedOn;
		this.orderDetails = orderDetails;
		this.reviews = reviews;
	}

	public Tour() {
		
	}
	
	public Tour(Integer tourId) {
		super();
		this.tourId = tourId;
	}

	public Tour(int i) {
		
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "tour_id", unique = true, nullable = false)
	public Integer getTourId() {
		return this.tourId;
	}

	public void setTourId(Integer tourId) {
		this.tourId = tourId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", nullable = false)
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name = "title", unique = true, nullable = false, length = 128)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "city_country", nullable = false, length = 64)
	public String getCityCountry() {
		return this.cityCountry;
	}

	public void setCityCountry(String cityCountry) {
		this.cityCountry = cityCountry;
	}

	@Column(name = "depiction", nullable = false, length = 16777215)
	public String getDepiction() {
		return this.depiction;
	}

	public void setDepiction(String depiction) {
		this.depiction = depiction;
	}

	@Column(name = "product_code", nullable = false, length = 20)
	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	@Column(name = "image", nullable = false)
	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Column(name = "price", nullable = false, precision = 12, scale = 0)
public float getPrice() {
return this.price;
}

public void setPrice(float price) {
this.price = price;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "publish_date", nullable = false, length = 10)
	public Date getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "last_updated_on", nullable = false, length = 19)
	public Date getLastUpdatedOn() {
		return this.lastUpdatedOn;
	}

	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tour")
	public Set<OrderDetail> getOrderDetails() {
	return this.orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tour")
	public Set<Review> getReviews() {
		TreeSet<Review> sortedReviews = new TreeSet<>(new Comparator<Review>() {
		@Override
		public int compare(Review review1, Review review2) {
			
			return review2.getReviewTime().compareTo(review1.getReviewTime());
		}
		});
		
		sortedReviews.addAll(reviews);
		return sortedReviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}
@Transient
public String getBaseImage() {
	this.baseImage = Base64.getEncoder().encodeToString(this.image);
	return  this.baseImage;
}

@Transient
public void setBaseImage(String baseImage) {
	this.baseImage = baseImage;
}

@Transient
public float getAverageRating() {
	float averageRating = 0.0f;
	float sum = 0.0f;
	
	if (reviews.isEmpty()) {
		return 0.0f;
	}
	
	for (Review review : reviews) {
		sum += review.getRating();		
	}
	
	averageRating = sum / reviews.size();
	
	return averageRating;		
}

@Transient
public String getRatingStars() {
	float averageRating = getAverageRating();
	
	return getRatingString(averageRating);
}

@Transient
public String getRatingString(float averageRating) {
	
	String result = "";
	
	int numberOfStarsOn = (int) averageRating;
	
	for (int i = 1; i <= numberOfStarsOn; i++) {
		result += "on,";		
	}
	
	int next = numberOfStarsOn + 1;
	
	if (averageRating > numberOfStarsOn) {
		result += "half,";
		next++;
	}
	
	for (int j = next; j <= 5; j++) {
		result += "off,";			
	}
	
	return result.substring(0, result.length() - 1);	
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((tourId == null) ? 0 : tourId.hashCode());
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
	Tour other = (Tour) obj;
	if (tourId == null) {
		if (other.tourId != null)
			return false;
	} else if (!tourId.equals(other.tourId))
		return false;
	return true;
}

}
