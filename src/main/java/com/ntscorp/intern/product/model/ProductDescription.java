package com.ntscorp.intern.product.model;

public class ProductDescription {
	private int id;
	private int productId;
	private String title;
	private String content;
	private String mapUrl;
	private String place_street;
	private String place_lot;
	private String place_name;
	private String tel;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMapUrl() {
		return mapUrl;
	}

	public void setMapUrl(String mapUrl) {
		this.mapUrl = mapUrl;
	}

	public String getPlace_street() {
		return place_street;
	}

	public void setPlace_street(String place_street) {
		this.place_street = place_street;
	}

	public String getPlace_lot() {
		return place_lot;
	}

	public void setPlace_lot(String place_lot) {
		this.place_lot = place_lot;
	}

	public String getPlace_name() {
		return place_name;
	}

	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "DisplayDescription [id=" + id + ", productId=" + productId + ", title=" + title + ", content=" + content
			+ ", mapUrl=" + mapUrl + ", place_street=" + place_street + ", place_lot=" + place_lot + ", place_name="
			+ place_name + ", tel=" + tel + "]";
	}
}