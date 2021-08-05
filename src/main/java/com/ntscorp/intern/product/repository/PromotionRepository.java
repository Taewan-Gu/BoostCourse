package com.ntscorp.intern.product.repository;

import java.util.List;

import com.ntscorp.intern.product.model.PromotionImage;

public interface PromotionRepository {
	public List<PromotionImage> findAllPromotionImages();
}