package com.ntscorp.intern.product.controller.response;

import java.util.List;

import com.ntscorp.intern.product.model.Promotion;

public class PromotionsResponse {
	private List<Promotion> promotions;

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	@Override
	public String toString() {
		return "PromotionsResponse [promotions=" + promotions + "]";
	}
}