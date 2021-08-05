package com.ntscorp.intern.product.controller.response;

import java.util.List;

import com.ntscorp.intern.product.model.PromotionImage;

public class PromotionsResponse {
	private List<PromotionImage> promotions;

	public List<PromotionImage> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<PromotionImage> promotions) {
		this.promotions = promotions;
	}

	@Override
	public String toString() {
		return "PromotionsResponse [promotions=" + promotions + "]";
	}
}