package com.bookory.server.services;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bookory.server.mapper.BookConvert;
import com.bookory.models.request.BookRequestDTO;
import com.bookory.models.request.StoreReportRequest;
import com.bookory.models.response.BookBasicInfoDTO;
import com.bookory.models.response.BookExtendInforDTO;
import com.bookory.models.response.BookFullInforDTO;
import com.bookory.models.response.BookSold;
import com.bookory.models.response.TagResponse;
import com.bookory.entity.BookEntity;
import com.bookory.entity.BookTagEntity;
import com.bookory.entity.TagEntity;
import com.bookory.server.repositories.BookRepository;
import com.bookory.server.repositories.BookTagRepository;
import com.bookory.server.repositories.TagRepository;
@Service
public class BookServices {
	@Autowired
	BookRepository bookRepository;
	@Autowired
	BookTagRepository bookTagRepository;
	@Autowired
	ImageStorageService imageStorageService;
	@Autowired
	BookConvert bookConvert;
	@Autowired
	TagRepository tagRepository;
	
	public List<BookBasicInfoDTO> getAllBook() throws IOException {
		List<BookEntity> bookEntities = bookRepository.findByStatus(0);
		Collections.shuffle(bookEntities);
	   int numberOfRandomBooks = 30;
	   bookEntities.subList(0, Math.min(numberOfRandomBooks, bookEntities.size()));
		List<BookBasicInfoDTO> bookBasicInfors = new ArrayList<>();
		for(BookEntity bookEntity:bookEntities) {
			BookBasicInfoDTO bookBasicInfor = bookConvert.toBookBasicInfoDTO(bookEntity);
			bookBasicInfors.add(bookBasicInfor);
		}
		return bookBasicInfors;
	}
	
	public List<BookBasicInfoDTO> getBooksByStoreId(long storeId) throws IOException {
		List<BookEntity> bookEntities = bookRepository.findByStoreEntityId(storeId);
		List<BookBasicInfoDTO> bookBasicInfors = new ArrayList<>();
		for(BookEntity bookEntity:bookEntities) {
			BookBasicInfoDTO bookBasicInfor = bookConvert.toBookBasicInfoDTO(bookEntity);
			bookBasicInfors.add(bookBasicInfor);
		}
		return bookBasicInfors;
	}

	public List<BookBasicInfoDTO> getBookByKeyword(String key) throws IOException {
		List<BookEntity> bookEntities = bookRepository
				.findByNameContainingOrAuthorContainingOrStoreEntityNameContaining(key, key, key);
		List<BookBasicInfoDTO> bookBasicInfors = new ArrayList<>();
		for(BookEntity bookEntity:bookEntities) {
			if(bookEntity.getStatus() != 2) {
				BookBasicInfoDTO bookBasicInfor = bookConvert.toBookBasicInfoDTO(bookEntity);
				bookBasicInfors.add(bookBasicInfor);
			}
		}
		return bookBasicInfors;
	}

	public List<BookExtendInforDTO> getAllBookByStoreIDAndStatus(long storeId, int status) throws IOException {
		List<BookEntity> bookEntities = bookRepository.findByStoreEntityIdAndStatus(storeId, status);
		List<BookExtendInforDTO> bookExtendInfors = new ArrayList<>();
		for(BookEntity bookEntity:bookEntities) {
			BookExtendInforDTO bookExtendInforDTO = bookConvert.toBookExtendInforDTO(bookEntity);
			bookExtendInfors.add(bookExtendInforDTO);
		}
		return bookExtendInfors;
	}

	public BookFullInforDTO getBookByID(long id) throws IOException {
		BookEntity bookEntity = bookRepository.findById(id).orElse(null);
		if (bookEntity != null) {
			BookFullInforDTO book = bookConvert.toBookFullInforDTO(bookEntity);
			List<TagEntity> tagEntities = bookTagRepository.findByBookId(bookEntity.getId());
			List<TagResponse> tagResponses = new ArrayList<>();
			for(TagEntity tagEntity:tagEntities) {
				tagResponses.add(new TagResponse(tagEntity.getId(), tagEntity.getName()));
			}
			book.setTags(tagResponses);
			return book;
		}
		return null;
	}

	public BookEntity addNewBook(BookRequestDTO book, MultipartFile[] files) throws IOException {
		BookEntity bookEntity = bookConvert.toBookEntity(book);
		if (files != null) {
			String filePath = imageStorageService.storeMultiFile(files);
			bookEntity.setImage(filePath);
		}
		bookEntity.setCreateDate(Date.valueOf(LocalDate.now()));
		bookEntity.setQuantitySold(0);
		bookEntity.setStatus(0);
		bookEntity = bookRepository.save(bookEntity);
		List<BookTagEntity> bookTagEntities = new ArrayList<>();
		for(long id:book.getTagId()) {
			BookTagEntity booktag = new BookTagEntity();
			booktag.setBook(bookEntity);
			booktag.setTag(tagRepository.findById(id).orElse(null));
			bookTagEntities.add(booktag);
		}
		bookTagRepository.saveAll(bookTagEntities);
		return bookEntity;
	}

	public BookEntity updateBook(long id, BookRequestDTO book, MultipartFile[] files) throws IOException {
		BookEntity bookEntity = bookRepository.findById(id).orElse(null);
		if(bookEntity!=null) {
			bookEntity = bookConvert.toBookEntity(book);
			bookRepository.deleteBookTagsByBookId(id);
			if (files != null) {
				String imagePaths = imageStorageService.storeMultiFile(files);
				bookEntity.setImage(imagePaths);
			}
			bookEntity.setId(id);
			bookEntity.setUpdateDate(Date.valueOf(LocalDate.now()));
			bookEntity =  bookRepository.save(bookEntity);
			List<BookTagEntity> bookTagEntities = new ArrayList<>();
			for(long tagid:book.getTagId()) {
				BookTagEntity booktag = new BookTagEntity();
				booktag.setBook(bookEntity);
				booktag.setTag(tagRepository.findById(tagid).orElse(null));
				bookTagEntities.add(booktag);
			}
			bookTagRepository.saveAll(bookTagEntities);
			return bookEntity;
		}else
			return null;
	}
	
	public BookEntity setBookStatus(long id, int status) {
		BookEntity bookEntity = bookRepository.findById(id).get();
		bookEntity.setStatus(status);
		return bookRepository.save(bookEntity);
	}

	public List<BookBasicInfoDTO> getListRelatedBooks(long categoryId) throws IOException {
		List<BookEntity> bookEntities = bookRepository.findByCategoryEntityIdAndStatus(categoryId, 0);
		List<BookBasicInfoDTO> bookBasics = new ArrayList<>();
		for(BookEntity bookEntity: bookEntities) {
			BookBasicInfoDTO bookBasic = bookConvert.toBookBasicInfoDTO(bookEntity);
			bookBasics.add(bookBasic);
		}
		return bookBasics;
	}

	public List<BookBasicInfoDTO> getBookForAddNewPromotion(long storeId, Date startDate, Date endDate) {
		List<BookEntity> bookEntities1 = bookRepository.findBooksByStoreIdAndPromotionIsNull(storeId);
		List<BookEntity> bookEntities2 = bookRepository.findBooksByStoreEntityIdAndDateNotBetween(storeId, startDate,
				endDate);
		bookEntities1.addAll(bookEntities2);
		List<BookBasicInfoDTO> bookBasics = new ArrayList<>();
		for(BookEntity bookEntity: bookEntities1) {
			BookBasicInfoDTO bookBasic = bookConvert.toBookBasicInfoDTO(bookEntity);
			bookBasics.add(bookBasic);
		}
		return bookBasics;
	}

	public List<BookBasicInfoDTO> getBookForUpdatePromotion(long storeId, long promotioneId) {
		List<BookEntity> bookEntities = bookRepository.findBookeByStoreEntityIdAndPromotionId(storeId, promotioneId);
		for (BookEntity bookEntity : bookEntities) {
			if (bookEntity.getPromotionEntity() != null) {
				bookEntity.setStatus(1);
			} else
				bookEntity.setStatus(0);
		}
		List<BookBasicInfoDTO> bookBasics = new ArrayList<>();
		for(BookEntity bookEntity: bookEntities) {
			BookBasicInfoDTO bookBasic = bookConvert.toBookBasicInfoDTO(bookEntity);
			bookBasics.add(bookBasic);
		}
		return bookBasics;
	}
	
	public List<BookSold> getBookSoldByStoreId(StoreReportRequest storeRequest){
		return bookRepository.findTopBooksByStoreIdAndDateRange(storeRequest.getStoreId(), storeRequest.getStartDate(),storeRequest.getEndDate());
	}
}
