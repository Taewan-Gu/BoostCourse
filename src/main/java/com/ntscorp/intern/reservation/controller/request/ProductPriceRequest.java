package com.ntscorp.intern.reservation.controller.request;

public class ProductPriceRequest {
	private int productPriceId;
	private int count;

	public int getProductPriceId() {
		return productPriceId;
	}

	public void setProductPriceId(int productPriceId) {
		this.productPriceId = productPriceId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "ProductPriceRequest [productPriceId=" + productPriceId + ", count=" + count + "]";
	}
}