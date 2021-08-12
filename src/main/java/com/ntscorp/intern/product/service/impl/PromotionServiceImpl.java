package com.ntscorp.intern.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntscorp.intern.product.model.Promotion;
import com.ntscorp.intern.product.repository.PromotionRepository;
import com.ntscorp.intern.product.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {
	private final PromotionRepository promotionRepository;

	@Autowired
	public PromotionServiceImpl(PromotionRepository promotionRepository) {
		this.promotionRepository = promotionRepository;
	}

	@Override
	public List<Promotion> getAllPromotions() {
		return promotionRepository.selectAllPromotions();
	}
}