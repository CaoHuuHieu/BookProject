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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookory.entity.TagEntity;
import com.bookory.server.helper.ResponseObject;
import com.bookory.server.services.TagServices;
@CrossOrigin
@RestController
@RequestMapping("/api/")
public class TagController {
	@Autowired
	TagServices tagServices;
	
	@GetMapping(value = "tag")
	@ResponseBody
	public ResponseEntity<ResponseObject> getAllTag() {
		List<TagEntity> tagEntities = tagServices.getALlTag();
		if (tagEntities!= null)
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", tagEntities));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
	}

	@GetMapping(value = "tag/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getTagByID(@PathVariable long id) {
		TagEntity tagEntity = tagServices.getTagByID(id);
		if (tagEntity != null)
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", tagEntity));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
	}

	@PutMapping(value = "tag/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> updateTag(@PathVariable long id, @RequestBody TagEntity tag) {
		TagEntity tagEntity = tagServices.updateTag(id, tag);
		if (tagEntity != null)
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", tagEntity));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
	}

	@PostMapping(value = "tag")
	@ResponseBody
	public ResponseEntity<ResponseObject> addNewTag(@RequestBody TagEntity tag) {
		TagEntity tagEntity = tagServices.addNewTag(tag);
		if (tagEntity != null)
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", tagEntity));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
	}

	@DeleteMapping(value = "tag/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> deleteTag(@PathVariable long id) {
		try {
			tagServices.deleteTag(id);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", false));
		}
	}

}
