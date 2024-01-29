package com.bookory.server.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookory.models.request.VnpayRequestDTO;
import com.bookory.server.helper.ResponseObject;
import com.bookory.server.services.VnpayService;

import jakarta.servlet.http.HttpServletRequest;
@CrossOrigin
@RestController
public class VnpayController {
	@Autowired
	VnpayService vnpayService;
	@PostMapping("/vnpay/service")
	public  ResponseEntity<ResponseObject>  registerService(@RequestBody VnpayRequestDTO vnpay, HttpServletRequest request) {
		try {
			String result =  vnpayService.registerService(vnpay, request);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công", result));
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện", ""));
		}
	    
	}
}
