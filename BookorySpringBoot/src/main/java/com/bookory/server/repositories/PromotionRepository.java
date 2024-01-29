package com.bookory.server.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bookory.entity.PromotionEntity;



public interface PromotionRepository extends JpaRepository<PromotionEntity, Long>{
	List<PromotionEntity> findByStoreEntityId(long storeId);
	List<PromotionEntity> findByStoreEntityIdAndStatus(long storeId, int status);
}
