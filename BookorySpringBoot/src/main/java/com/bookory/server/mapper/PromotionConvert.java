package com.bookory.server.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookory.models.request.PromotionRequestDTO;
import com.bookory.entity.PromotionEntity;
import com.bookory.server.repositories.StoreRepository;

@Component
public class PromotionConvert {
@Autowired
StoreRepository storeRepository;
	public PromotionEntity toPromotionEntity(PromotionRequestDTO promotion) {
		PromotionEntity promotionEntity = new PromotionEntity();
		if(promotion.getStoreId() != 0)
			promotionEntity.setStoreEntity(storeRepository.findById(promotion.getStoreId()).orElse(null));
		promotionEntity.setName(promotion.getName());
		promotionEntity.setDiscount(promotion.getDiscount());
		promotionEntity.setStartDate(promotion.getStartDate());
		promotionEntity.setEndDate(promotion.getEndDate());
		promotionEntity.setCreateDate(promotion.getCreateDate());
		promotionEntity.setUpdateDate(promotion.getUpdateDate());
		promotionEntity.setStatus(promotion.getStatus());
		return promotionEntity;
	}

}
