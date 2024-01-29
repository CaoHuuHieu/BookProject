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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookory.entity.ContactEntity;
import com.bookory.server.helper.EmailProvider;
import com.bookory.server.helper.ResponseObject;
import com.bookory.server.services.ContactServices;
@CrossOrigin
@RestController
@RequestMapping("/api/")
public class ContactController {
	@Autowired
	ContactServices contactServices;
	@Autowired
	EmailProvider emailProvider;
	@GetMapping(value = "contact")
	@ResponseBody
	public ResponseEntity<ResponseObject> getAllContact() {
		List<ContactEntity> contactEntities = contactServices.getAllContact();
		if (contactEntities!= null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", contactEntities));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Không tìm thấy dữ liệu", ""));
	}

	@GetMapping(value = "contact/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getContactByID(@PathVariable long id) {
		ContactEntity contactEntity = contactServices.getContactByID(id);
		if (contactEntity != null)
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", contactEntity));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Danh sách rỗng!", ""));
	}

	@PostMapping(value = "contact")
	@ResponseBody
	public ResponseEntity<ResponseObject> addNewContact(@RequestBody ContactEntity contact) {
		ContactEntity contactEntity = contactServices.addNewContact(contact);
		if (contactEntity != null)
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", contactEntity));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));

	}
	@DeleteMapping(value = "contact/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> deleteContact(@PathVariable long id) {
		try {
			contactServices.deleteContact(id);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", false));
		}
	
	}

	@PostMapping(value = "contact/reply")
	@ResponseBody
	public ResponseEntity<ResponseObject> replyContact(@RequestBody ContactEntity contact) {
		try {
			emailProvider.sendEmail(contact.getGmail(), contact.getSubject(), contact.getContent());
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", ""));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}

}
