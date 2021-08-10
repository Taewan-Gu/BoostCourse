package com.ntscorp.intern.product.controller.response;

import java.util.List;

import com.ntscorp.intern.product.model.Promotion;

public class PromotionsResponse {
	private final List<Promotion> promotions;

	public PromotionsResponse(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	@Override
	public String toString() {
		return "PromotionsResponse [promotions=" + promotions + "]";
	}
}