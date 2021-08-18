package com.ntscorp.intern.reservation.model;

public class ProductReservation {
	private int productId;
	private int categoryId;
	private String title;
	private String openingHours;
	private String place;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@Override
	public String toString() {
		return "ReservationProduct [productId=" + productId + ", title=" + title + ", openingHours=" + openingHours
			+ ", place=" + place + "]";
	}
}