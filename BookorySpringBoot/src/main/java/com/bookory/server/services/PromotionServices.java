package com.bookory.server.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookory.server.mapper.PromotionConvert;
import com.bookory.models.request.PromotionRequestDTO;
import com.bookory.entity.BookEntity;
import com.bookory.entity.PromotionEntity;
import com.bookory.server.repositories.BookRepository;
import com.bookory.server.repositories.PromotionRepository;
import com.bookory.server.repositories.StoreRepository;


@Service
public class PromotionServices {
	@Autowired
	PromotionRepository promotionRepository;
	@Autowired
	StoreServices storeServices;
	@Autowired
	PromotionConvert promotionConvert;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	StoreRepository storeRepository;
	public List<PromotionEntity> getAllPromotionByStoreId(long storeId){
		return promotionRepository.findByStoreEntityId(storeId);
	}

	public List<PromotionEntity> getAllPromotionByStoreIdAndStatus(long storeId, int status){
		return promotionRepository.findByStoreEntityIdAndStatus(storeId, status);
	}
	
	public PromotionEntity getPromotionById(long id){
		return promotionRepository.findById(id).orElse(null);
	}
	
	public PromotionEntity addNewPromotion(PromotionRequestDTO promotion){
		PromotionEntity promotionEntity = promotionConvert.toPromotionEntity(promotion);
		promotionEntity.setStoreEntity(storeRepository.findById(promotion.getStoreId()).orElse(null));
		promotionEntity.setCreateDate(Date.valueOf(LocalDate.now()));
		promotionEntity.setStatus(0);
		promotionEntity =  promotionRepository.save(promotionEntity);
		//cập nhật trạng thái khuyến mãi cho sách
		for(Long id:promotion.getBookIds()) {
			BookEntity bookEntity = bookRepository.findById(id).orElse(null);
			if(bookEntity != null) {
				bookEntity.setPromotionEntity(promotionEntity);
				bookRepository.save(bookEntity);
			}
		}
		return promotionEntity;
	}
	
	public PromotionEntity updatePromotion(long id, PromotionRequestDTO promotion){
		PromotionEntity promotionEntity =  promotionConvert.toPromotionEntity(promotion);
		promotionEntity.setId(id);
		promotionEntity.setStoreEntity(storeRepository.findById(promotion.getStoreId()).orElse(null));
		promotionEntity.setUpdateDate(Date.valueOf(LocalDate.now()));
		promotionEntity = promotionRepository.save(promotionEntity);
		bookRepository.setPromotioneNullByPromotioneId(id);
		bookRepository.updatePromotionByBookIdInAndPromotionId(promotion.getBookIds(), promotionEntity);
		return promotionEntity;
	}

	public PromotionEntity updatePromotionStatus(long id, int status){
		PromotionEntity promotionEntity = getPromotionById(id);
		promotionEntity.setStatus(status);
		if(status == 2) {
			List<BookEntity> books = bookRepository.findByPromotionEntityId(id);
			for(BookEntity book:books) {
				book.setPromotionEntity(null);
				bookRepository.save(book);
			}
			
		}
		return promotionRepository.save(promotionEntity);
	}
}
