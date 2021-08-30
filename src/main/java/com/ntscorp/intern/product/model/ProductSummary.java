package com.ntscorp.intern.product.model;

public class ProductSummary {
	private int id;
	private int displayInfoId;
	private String title;
	private String place;
	private String placeStreet;
	private String openingHours;
	private String content;
	private String productImageUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getPlaceStreet() {
		return placeStreet;
	}

	public void setPlaceStreet(String placeStreet) {
		this.placeStreet = placeStreet;
	}

	public String getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getProductImageUrl() {
		return productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	@Override
	public String toString() {
		return "ProductSummary [id=" + id + ", displayInfoId=" + displayInfoId + ", title=" + title + ", place=" + place
			+ ", placeStreet=" + placeStreet + ", openingHours=" + openingHours + ", content=" + content
			+ ", productImageUrl=" + productImageUrl + "]";
	}
}