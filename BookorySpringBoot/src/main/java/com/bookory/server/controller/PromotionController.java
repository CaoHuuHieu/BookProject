package com.bookory.server.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookory.models.request.PromotionRequestDTO;
import com.bookory.models.response.BookBasicInfoDTO;
import com.bookory.entity.PromotionEntity;
import com.bookory.server.helper.ResponseObject;
import com.bookory.server.services.BookServices;
import com.bookory.server.services.PromotionServices;
@CrossOrigin
@RestController
@RequestMapping("/api/")
public class PromotionController {
	@Autowired
	PromotionServices promotionServices;
	@Autowired
	BookServices bookServices;
	
	@GetMapping(value = "promotions/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getAllPromotionByStoreID(@PathVariable long id) {
		List<PromotionEntity> promotionEntities;
		promotionEntities = promotionServices.getAllPromotionByStoreId(id);
		if (promotionEntities.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", promotionEntities));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
		}
	}
	@GetMapping(value = "promotions/book/{storeId}")
	@ResponseBody
	public ResponseEntity<ResponseObject>  getProductForPromotion(@PathVariable("storeId") long storeId, @RequestParam("startDate") Date startDate,  @RequestParam("endDate") Date endDate) {
		List<BookBasicInfoDTO> books = bookServices.getBookForAddNewPromotion(storeId,startDate, endDate);
		if(books != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", books));
		}else
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
		
	}
	@GetMapping(value = "promotions/book/{storeId}/{promotionId}")
	@ResponseBody
	public ResponseEntity<ResponseObject>  getProductForUpdatePromotion(@PathVariable("storeId") long storeId, 
			@PathVariable("promotionId") long promotionId){
		List<BookBasicInfoDTO> books = bookServices.getBookForUpdatePromotion(storeId, promotionId);
		if(books != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", books));
		}else
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
		
	}
	@GetMapping(value = "promotion/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getPromotionByID(@PathVariable long id) {
		PromotionEntity promotionEntity = promotionServices.getPromotionById(id);
		if (promotionEntity != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", promotionEntity));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
		}
	}

	@PostMapping(value = "promotion")
	@ResponseBody
	public ResponseEntity<ResponseObject> addNewPromotion(@RequestBody PromotionRequestDTO promotion) {
		PromotionEntity promotionEntity = promotionServices.addNewPromotion(promotion);
		if (promotionEntity != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", promotionEntity));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}

	@DeleteMapping(value = "promotion/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> deletePromotion(@PathVariable long id) {
		PromotionEntity promotionEntity = promotionServices.updatePromotionStatus(id, 2);
		if (promotionEntity != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", promotionEntity));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}

	@PutMapping(value = "promotion/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> updatePromotion(@PathVariable Long id,
			@RequestBody PromotionRequestDTO promotion) {

		PromotionEntity promotionEntity = promotionServices.updatePromotion(id, promotion);

		if (promotionEntity != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", promotionEntity));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}

	}

	@PutMapping(value = "promotion/{id}/{status}")
	@ResponseBody
	public ResponseEntity<ResponseObject> updatePromotionStatus(@PathVariable Long id, @PathVariable int status) {
		PromotionEntity promotionEntity = promotionServices.updatePromotionStatus(id, status);
		if (promotionEntity != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", promotionEntity));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}
}
