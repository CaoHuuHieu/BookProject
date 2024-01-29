package com.bookory.server.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bookory.models.request.StoreRequestDTO;
import com.bookory.models.response.StoreResponseDTO;
import com.bookory.entity.StoreEntity;
import com.bookory.server.helper.ResponseObject;
import com.bookory.server.services.StoreServices;
import com.fasterxml.jackson.databind.ObjectMapper;
@CrossOrigin
@RestController
@RequestMapping("/api/")
public class StoreController {
	@Autowired
	StoreServices storeServices;
	@GetMapping(value = "store")
	@ResponseBody
	public ResponseEntity<ResponseObject> getAllStore() {
		try {
			List<StoreResponseDTO> storeResponses = storeServices.getAllStore();
			if (storeResponses!= null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thực hiện thành công!", storeResponses));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu", ""));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện", ""));
		}

	}
	
	@GetMapping(value = "store/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getStoreByID(@PathVariable long id) throws IOException {
		try {
			StoreResponseDTO store = storeServices.getStoreByID(id);
			if (store != null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thực hiện thành công!", store));
			} else
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện", ""));
		}
	}

	
	
	@PostMapping("store")
	public ResponseEntity<ResponseObject> addNewStore(@RequestParam("object") String object,
			@RequestParam(name = "avatar", required = false) MultipartFile avatar,
			@RequestParam(name = "coverimage", required = false) MultipartFile coverimage) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			StoreRequestDTO store = mapper.readValue(object, StoreRequestDTO.class);
			storeServices.addNewStore(store, avatar, coverimage);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));

		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", false));
		}
	}
	@PostMapping("store/{storeId}/{serviceId}")
	public ResponseEntity<ResponseObject> updateStoreEndDate(@PathVariable long storeId, @PathVariable long serviceId){
		try {
			if(storeServices.updateStoreEndDate(storeId, serviceId) != null)
				return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
			else
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(500, "Dữ liệu không hợp lệ!", false));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", false));
		}

	}
	@PostMapping("store/{id}")
	public ResponseEntity<ResponseObject> udpateStore(@PathVariable long id, @RequestParam("object") String object,
			@RequestParam(name = "avatar", required = false) MultipartFile avatar,
			@RequestParam(name = "coverimage", required = false) MultipartFile coverimage) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			StoreRequestDTO store = mapper.readValue(object, StoreRequestDTO.class);
			storeServices.udpateStore(id, store, avatar, coverimage);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));

		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", false));
		}
	}

	@DeleteMapping(value = "store/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> deleteStore(@PathVariable long id) {
		StoreEntity storeEntity = storeServices.deleteStore(id);
		if (storeEntity != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseObject(500, "Thao tác không được thực hiện!", false));
	}

}
