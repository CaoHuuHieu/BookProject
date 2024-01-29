package com.bookory.server.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bookory.entity.SlideEntity;
import com.bookory.server.helper.ResponseObject;
import com.bookory.server.services.SlideService;
@CrossOrigin
@RestController
@RequestMapping("/api/")
public class SlideController {
	@Autowired
	SlideService slideService;
	@GetMapping("/slide")
	public ResponseEntity<ResponseObject> getAllSlide(){
		try {
			List<SlideEntity> slides = slideService.getAllSlide();
			if(slides.size() > 0) 
				return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", slides));
			else
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu !", ""));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", false));
		}
	}
	@GetMapping("/slide/show")
	public ResponseEntity<ResponseObject> getSlideVisible(){
		try {
			List<SlideEntity> slides = slideService.getSlideVisible();
			if(slides.size() > 0) 
				return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", slides));
			else
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu !", ""));
		
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", false));
		}
	}
	@PostMapping("/slide")
	public ResponseEntity<ResponseObject> addNewSlide(@RequestParam MultipartFile file){
		try {
			slideService.addNewSlide(file); 
				return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", false));
		}
	}
	
	@PutMapping("/slide/{id}")
	public ResponseEntity<ResponseObject> setSlideVisible(@PathVariable long id){
		try {
			slideService.updateStatus(id); 
				return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", false));
		}
	}
	@DeleteMapping("/slide/{id}")
	public ResponseEntity<ResponseObject> deleteSlide(@PathVariable long id){
		try {
			slideService.deleteSlide(id); 
				return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", false));
		}
	}
}
