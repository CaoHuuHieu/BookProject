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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bookory.models.request.CategoryRequestDTO;
import com.bookory.entity.CategoryEntity;
import com.bookory.server.helper.ResponseObject;
import com.bookory.server.services.CategoryServices;
import com.fasterxml.jackson.databind.ObjectMapper;
@CrossOrigin
@RestController
@RequestMapping("/api/")
public class CategoryController {
	@Autowired
	CategoryServices categoryServices;
	/***********************************************
	 ********** API FOR CATEGORY ***********
	 **********************************************
	 **********************************************/
	@GetMapping(value = "category")
	@ResponseBody
	public ResponseEntity<ResponseObject> getAllCategory() {
		List<CategoryEntity> categories = categoryServices.getAllCategories();
		if (categories!= null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công", categories));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject(404, "Không tìm thấy dữ liệu", ""));
		}
	}

	@GetMapping(value = "category/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getCategoryByID(@PathVariable long id) {
		CategoryEntity category = categoryServices.getCategoriesByID(id);
		if (category != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công", category));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject(404, "Không tìm thấy danh mục id: " + id, ""));
		}
	}

	@PutMapping(value = "category/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> updateCategory(@PathVariable long id, @RequestParam("object") String  object, @RequestParam(name = "file", required = false) MultipartFile file) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			CategoryRequestDTO category = mapper.readValue(object, CategoryRequestDTO.class);
			CategoryEntity categoryEntity = categoryServices.updateCategory(id, category, file);
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thực hiện thành công", categoryEntity));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}

	@PostMapping(value = "category")
	@ResponseBody
	public ResponseEntity<ResponseObject> addNewCategory(@RequestParam("object")  String object, @RequestParam("file") MultipartFile file) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			CategoryRequestDTO category = mapper.readValue(object, CategoryRequestDTO.class);
			CategoryEntity categoryEntity = categoryServices.addNewCategory(category, file);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công", categoryEntity));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}

	@DeleteMapping(value = "category/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> deleteCategory(@PathVariable long id) {
		CategoryEntity categoryEntity = categoryServices.deleteCategory(id);
		if (categoryEntity != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công", categoryEntity));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseObject(500, "Danh sách rỗng", ""));

	}

}
