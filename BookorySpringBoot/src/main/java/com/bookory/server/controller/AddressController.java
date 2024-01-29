package com.bookory.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookory.models.request.AddressRequestDTO;
import com.bookory.models.response.AddressResponseDTO;
import com.bookory.entity.AddressEntity;
import com.bookory.entity.UserEntity;
import com.bookory.server.helper.ResponseObject;
import com.bookory.server.services.AddressServices;
@CrossOrigin
@RestController
@RequestMapping("/api/")
public class AddressController {
	@Autowired
	AddressServices addressServices;
	
	@GetMapping("address/{addressId}")
	public ResponseEntity<ResponseObject> getAddress(@PathVariable long addressId){
		AddressResponseDTO address =  addressServices.getAddressById(addressId);
		if(address != null)
			 return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thành công!", address));
		else
			 return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
	}
	@PutMapping("address/{addressId}")
	public ResponseEntity<ResponseObject> udpateAddress(@PathVariable Long addressId, @RequestBody AddressRequestDTO address){
		AddressEntity addressEntity =  addressServices.udpateAddress(addressId, address);
		if(addressEntity != null)
			 return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thành công!", address));
		else
			 return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
	}
	
	@PostMapping("address/{userId}")
	public ResponseEntity<ResponseObject> addAddressForUser(@PathVariable Long userId, @RequestBody AddressRequestDTO address){
		UserEntity userEntity =  addressServices.addAddressForUser(userId, address);
		if(userEntity != null)
			 return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thành công!", address));
		else
			 return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Tài khoản không tồn tại!", ""));
	}
}
