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

import com.bookory.models.response.ServicePackResponseDTO;
import com.bookory.entity.ServicePackEntity;
import com.bookory.server.helper.ResponseObject;
import com.bookory.server.services.ServicesPackServices;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;
@CrossOrigin
@RestController
@RequestMapping("/api/")
public class ServiceController {
	@Autowired
	ServicesPackServices servicesPackServices;
	@GetMapping(value = "service")
	@ResponseBody
	public ResponseEntity<ResponseObject> getAllServices() {
		try {
			List<ServicePackResponseDTO> servicePackEntities = servicesPackServices.getAllServices();
			if (servicePackEntities!= null)
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thực hiện thành công!", servicePackEntities));
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject(404, "Không tìm thấy dữ liệu", ""));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(500, "Thao tác không được thực hiện", ""));
		}
	}

	@GetMapping(value = "service/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getServicerByID(@PathVariable long id) {
		try {
			ServicePackResponseDTO servicePackEntity = servicesPackServices.getServicesByID(id);
			if (servicePackEntity != null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thực hiện thành công!", servicePackEntity));
			} else
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu", ""));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}

	@PostMapping("/service")
	public ResponseEntity<ResponseObject> addNewServicePack(@RequestParam("object") String object,
			@RequestParam("file") MultipartFile file) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			ServicePackEntity servicePack = mapper.readValue(object, ServicePackEntity.class);
			ServicePackEntity servicePackEntity = servicesPackServices.addNewService(servicePack, file);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", servicePackEntity));
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}

	}

	@PostMapping("/service/{id}")
	public ResponseEntity<ResponseObject> updateServicePack(@PathVariable long id,
			@RequestParam("object") String object, @RequestParam(value = "file", required = false) MultipartFile file,
			HttpSession session) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			ServicePackEntity servicePack = mapper.readValue(object, ServicePackEntity.class);
			servicesPackServices.updateService(id, servicePack, file);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", false));
		}
	}

	@DeleteMapping(value = "service/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> deleteServicePack(@PathVariable long id) {
		ServicePackEntity servicePackEntity = servicesPackServices.deleteService(id);
		if (servicePackEntity != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseObject(500, "Thao tác không được thực hiện!", false));
	}

}
