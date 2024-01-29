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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bookory.models.request.BookRequestDTO;
import com.bookory.models.request.StoreReportRequest;
import com.bookory.models.response.BookBasicInfoDTO;
import com.bookory.models.response.BookExtendInforDTO;
import com.bookory.models.response.BookFullInforDTO;
import com.bookory.models.response.BookSold;
import com.bookory.entity.BookEntity;
import com.bookory.server.helper.ResponseObject;
import com.bookory.server.services.BookServices;
import com.fasterxml.jackson.databind.ObjectMapper;
@CrossOrigin
@RestController
@RequestMapping("/api/")
public class BookController {

	@Autowired
	BookServices bookServices;
	@GetMapping(value = "book/{id}")
	public ResponseEntity<ResponseObject> getBookByID(@PathVariable long id) {
		try {
			
			BookFullInforDTO bookEntity = bookServices.getBookByID(id);
			if (bookEntity != null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thành công!", bookEntity));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}

	@GetMapping(value = "book/search/{key}")
	public ResponseEntity<ResponseObject> getBookByID(@PathVariable String key) {
		try {
			List<BookBasicInfoDTO> bookBasicInfoDTOs = bookServices.getBookByKeyword(key);
			if (bookBasicInfoDTOs!= null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thực hiện thành công!", bookBasicInfoDTOs));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện", ""));
		}
	}

	@GetMapping(value = "books")
	public ResponseEntity<ResponseObject> getAllBook() {
		try {
			List<BookBasicInfoDTO> bookBasicInfoDTOs = bookServices.getAllBook();
			if (bookBasicInfoDTOs!= null)
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thực hiện thành công!", bookBasicInfoDTOs));
			else
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}

	@GetMapping(value = "book/related/{id}")
	public ResponseEntity<ResponseObject> getListRelatedBook(@PathVariable long id) {
		try {
			List<BookBasicInfoDTO> relatedBookEntities = bookServices.getListRelatedBooks(id);
			if (relatedBookEntities!= null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thực hiện thành công!", relatedBookEntities));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}

	@GetMapping(value = "books/{storeId}")
	public ResponseEntity<ResponseObject> getAllBookByStoreID(@PathVariable Long storeId) throws IOException {
		try {
			List<BookBasicInfoDTO> bookStoreEntities = bookServices.getBooksByStoreId(storeId);
			if (bookStoreEntities!= null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thực hiện thành công!", bookStoreEntities));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}

	@GetMapping(value = "books/{storeId}/{status}")
	public ResponseEntity<ResponseObject> getAllBookByStoreIDAndStatus(@PathVariable Long storeId,
			@PathVariable int status) {
		try {
			List<BookExtendInforDTO> bookStoreEntities;
			bookStoreEntities = bookServices.getAllBookByStoreIDAndStatus(storeId, status);
			if (bookStoreEntities!= null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thực hiện thành công!", bookStoreEntities));
			} else
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}

	@PostMapping(value = "book")
	public ResponseEntity<ResponseObject> addNewBook(@RequestParam(name = "object") String object,
			@RequestParam(name = "files", required = false) MultipartFile[] files) {
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			BookRequestDTO book = mapper.readValue(object, BookRequestDTO.class);
			bookServices.addNewBook(book, files);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}

	@PostMapping(value = "book/{id}")
	public ResponseEntity<ResponseObject> updateBook(@PathVariable Long id,
			@RequestParam(name = "object") String object,
			@RequestParam(name = "files", required = false) MultipartFile[] files) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			BookRequestDTO book = mapper.readValue(object, BookRequestDTO.class);
			bookServices.updateBook(id, book, files);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}

	@DeleteMapping(value = "book/{id}")
	public ResponseEntity<ResponseObject> deleteBook(@PathVariable long id) {
		BookEntity bookEntity = bookServices.setBookStatus(id, 2);
		if (bookEntity != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", false));
		}
	}
	
	@GetMapping("store/booksold")
	public ResponseEntity<ResponseObject> getBookStoreForStore(@RequestBody StoreReportRequest storeRequestReport)
	{
		List<BookSold> bookSolds = bookServices.getBookSoldByStoreId(storeRequestReport);
		if (bookSolds.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", bookSolds));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
		}
	}
}
