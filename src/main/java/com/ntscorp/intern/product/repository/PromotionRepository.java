package com.ntscorp.intern.product.repository;

import java.util.List;

import com.ntscorp.intern.product.model.Promotion;

public interface PromotionRepository {
	public List<Promotion> selectAllPromotions();
}