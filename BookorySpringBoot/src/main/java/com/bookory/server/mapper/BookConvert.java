package com.bookory.server.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookory.models.request.BookRequestDTO;
import com.bookory.models.response.BookBasicInfoDTO;
import com.bookory.models.response.BookExtendInforDTO;
import com.bookory.models.response.BookFullInforDTO;
import com.bookory.entity.BookEntity;
import com.bookory.entity.TagEntity;
import com.bookory.server.repositories.CategoryRepository;
import com.bookory.server.repositories.PromotionRepository;
import com.bookory.server.repositories.StoreRepository;
import com.bookory.server.repositories.TagRepository;


@Component
public class BookConvert {
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	TagRepository tagRepository;
	@Autowired
	StoreRepository storeRepository;
	@Autowired
	PromotionRepository promotionRepository;
	public BookBasicInfoDTO toBookBasicInfoDTO(BookEntity bookEntity) {
		BookBasicInfoDTO bookBasic = new BookBasicInfoDTO();
		bookBasic.setId(bookEntity.getId());
		bookBasic.setAuthor(bookEntity.getAuthor());
		bookBasic.setDiscount((bookEntity.getPromotionEntity() == null) ? 0:(bookEntity.getPromotionEntity().getDiscount()));
		if(bookEntity.getImage() != null)
			bookBasic.setImage(bookEntity.getImage().split(",")[0]);
		else 
			bookBasic.setImage(null);
		bookBasic.setName(bookEntity.getName());
		bookBasic.setPrice(bookEntity.getPrice());
		bookBasic.setQuantity(bookEntity.getQuantity());
		bookBasic.setQuantitySold(bookEntity.getQuantitySold());
		bookBasic.setStatus(bookEntity.getStatus());
		bookBasic.setStoreName(bookEntity.getStoreEntity().getName());
		return bookBasic;
	}
	public BookExtendInforDTO toBookExtendInforDTO(BookEntity bookEntity) {
		BookExtendInforDTO bookExtend = new BookExtendInforDTO();
		bookExtend.setId(bookEntity.getId());
		bookExtend.setAuthor(bookEntity.getAuthor());
		if(bookEntity.getImage() != null)
			bookExtend.setImages(bookEntity.getImage().split(","));
		else 
			bookExtend.setImages(null);
		bookExtend.setName(bookEntity.getName());
		bookExtend.setPrice(bookEntity.getPrice());
		bookExtend.setQuantity(bookEntity.getQuantity());
		bookExtend.setStatus(bookEntity.getStatus());
		bookExtend.setQuantitySold(bookEntity.getQuantitySold());
		return bookExtend;
	}
	
	public BookFullInforDTO toBookFullInforDTO(BookEntity bookEntity) {
		BookFullInforDTO bookFullInfor = new BookFullInforDTO();
		bookFullInfor.setId(bookEntity.getId());
		bookFullInfor.setName(bookEntity.getName());
		bookFullInfor.setAuthor(bookEntity.getAuthor());
		bookFullInfor.setCategoryId(bookEntity.getCategoryEntity().getId());
		bookFullInfor.setCategory(bookEntity.getCategoryEntity().getName());
		bookFullInfor.setPublishing(bookEntity.getPublishing());
		bookFullInfor.setPublishingYear(bookEntity.getPublishingYear());		
		bookFullInfor.setPromotionId((bookEntity.getPromotionEntity() == null) ? 0:(bookEntity.getPromotionEntity().getId()));
		bookFullInfor.setDiscount((bookEntity.getPromotionEntity() == null) ? 0:(bookEntity.getPromotionEntity().getDiscount()));
		bookFullInfor.setPageNumber(bookEntity.getPageNumber());
		bookFullInfor.setLength(bookEntity.getLength());
		bookFullInfor.setWidth(bookEntity.getWidth());
		bookFullInfor.setHeight(bookEntity.getHeight());
		bookFullInfor.setWeight(bookEntity.getWeight());
		bookFullInfor.setQuantity(bookEntity.getQuantity());
		bookFullInfor.setQuantitySold(bookEntity.getQuantitySold());
		bookFullInfor.setPrice(bookEntity.getPrice());
		bookFullInfor.setCreateDate(bookEntity.getCreateDate());
		bookFullInfor.setUpdateDate(bookEntity.getUpdateDate());
		bookFullInfor.setDescription(bookEntity.getDescription());
		bookFullInfor.setStatus(bookEntity.getStatus());
		if(bookEntity.getImage() != null)
			bookFullInfor.setImages(bookEntity.getImage().split(","));
		else
			bookFullInfor.setImages(null);
		return bookFullInfor;
	}
	public BookEntity toBookEntity(BookRequestDTO book) {
		BookEntity bookEntity = new BookEntity();
		bookEntity.setName(book.getName());
		bookEntity.setAuthor(book.getAuthor());
		System.out.println(book.getImage());
		bookEntity.setImage(book.getImage());
		
		if(book.getCategoryId() != 0)
			bookEntity.setCategoryEntity(categoryRepository.findById(book.getCategoryId()).orElse(null));	
		bookEntity.setPublishing(book.getPublishing());
		bookEntity.setPublishingYear(book.getPublishingYear());	
		if(book.getStoreId() != 0)
			bookEntity.setStoreEntity(storeRepository.findById(book.getStoreId()).orElse(null));
		if(book.getPromotionId() != 0)
			bookEntity.setPromotionEntity(promotionRepository.findById(book.getPromotionId()).orElse(null));
		bookEntity.setPageNumber(book.getPageNumber());
		bookEntity.setLength(book.getLength());
		bookEntity.setWidth(book.getWidth());
		bookEntity.setHeight(book.getHeight());
		bookEntity.setWeight(book.getWeight());
		bookEntity.setQuantity(book.getQuantity());
		bookEntity.setQuantitySold(book.getQuantitySold());
		bookEntity.setPrice(book.getPrice());
		bookEntity.setCreateDate(book.getCreateDate());
		bookEntity.setUpdateDate(book.getUpdateDate());
		bookEntity.setDescription(book.getDescription());
		bookEntity.setStatus(book.getStatus());
		List<TagEntity> tags = new ArrayList<>();
		for (Long tagId : book.getTagId()) {
			TagEntity tag = tagRepository.findById(tagId).get();
			tags.add(tag);
		}
		return bookEntity;
	}
}
