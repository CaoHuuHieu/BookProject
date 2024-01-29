package com.bookory.server.controller;


import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.bookory.models.request.OrderRequestDTO;
import com.bookory.models.response.OrderResponseDTO;
import com.bookory.models.response.OrderResponseForStore;
import com.bookory.server.helper.ResponseObject;
import com.bookory.server.services.OrderServices;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public class OrderController {
	@Autowired
	OrderServices orderServices;

	@GetMapping("store/orders/{storeId}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getOrdersByStoreId( @PathVariable long storeId) {
		try {
			List<OrderResponseForStore> orders = orderServices.getOrdersByStoreId(storeId);
			if(orders != null)
				return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", orders));
			else 
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", orders));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}
	@GetMapping("store/order/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getOrdersById( @PathVariable long id) {
		try {
			OrderResponseForStore orders = orderServices.getOrdersById(id);
			if(orders != null)
				return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", orders));
			else 
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", orders));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}
	@PostMapping("order")
	@ResponseBody
	public ResponseEntity<ResponseObject> addNewOrder( @RequestBody OrderRequestDTO order) {
		try {
			
			orderServices.addNewOrder(order);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}
	
	@GetMapping("order/{userId}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getOrders( @PathVariable long userId) {
		try {
			List<OrderResponseDTO> orders = orderServices.getOrderByUserId(userId);
			if(orders != null)
				return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", orders));
			else
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}
	@PutMapping("order/{id}/{status}")
	@ResponseBody
	public ResponseEntity<ResponseObject> udpateOrderStatus(@PathVariable long id, @PathVariable int status) {
		try {
			orderServices.updateOrderStatus(id, status);
				return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
		
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}
}
