package com.bookory.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookory.models.request.ReviewRequestDTO;
import com.bookory.models.response.ReviewResponseDTO;
import com.bookory.server.helper.ResponseObject;
import com.bookory.server.services.OrderServices;
import com.bookory.server.services.ReviewServices;
@CrossOrigin
@RestController
@RequestMapping("/api/")
public class ReviewController {
	@Autowired
	ReviewServices reviewServices;
	@Autowired
	OrderServices orderServices;
	
	@GetMapping("/book/review/{bookId}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getReviewForBook(@PathVariable("bookId") long id) {
		try {
			List<ReviewResponseDTO> reviews = reviewServices.getAllReviewByBookId(id);
			if(reviews != null)
				return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", reviews));
			else 
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}
	@PostMapping("/review")
	@ResponseBody
	public ResponseEntity<ResponseObject> addReview(@RequestBody ReviewRequestDTO review) {
		try {
			reviewServices.addNewReview(review);
			orderServices.updateOrderStatus(review.getOrderId(), 5);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}
	
}
