package com.bookory.server.controller.endpoint;

import com.bookory.models.request.StoreReportRequest;
import com.bookory.models.response.BookBasicInfoDTO;
import com.bookory.models.response.BookFullInforDTO;
import com.bookory.models.response.BookSold;
import com.bookory.server.constant.APIConstant;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(APIConstant.API_BOOKS)
public interface BookEndpoint {
    @GetMapping(value = "{id}")
    BookFullInforDTO getBookByID(@PathVariable long id);

    @GetMapping(value = "search/{key}")
    List<BookBasicInfoDTO> getBookByID(@PathVariable String key);

    @GetMapping
    List<BookBasicInfoDTO> getAllBook();

    @GetMapping(value = "related/{id}")
    List<BookBasicInfoDTO> getListRelatedBook(@PathVariable long id);

    @GetMapping(value = "{storeId}")
    List<BookBasicInfoDTO> getAllBookByStoreID(@PathVariable Long storeId);

    @GetMapping(value = "{storeId}/{status}")
    List<BookBasicInfoDTO> getAllBookByStoreIDAndStatus(@PathVariable Long storeId, @PathVariable int status);

    @PostMapping
    List<BookBasicInfoDTO> addNewBook(@RequestParam(name = "object") String object,
                                      @RequestParam(name = "files", required = false) MultipartFile[] files);

    @PostMapping(value = "{id}")
    void updateBook(@PathVariable Long id,
                    @RequestParam(name = "object") String object,
                    @RequestParam(name = "files", required = false) MultipartFile[] files);

    @DeleteMapping(value = "book/{id}")
    void deleteBook(@PathVariable long id);

    @GetMapping("store/booksold")
    List<BookSold> getBookStoreForStore(@RequestBody StoreReportRequest storeRequestReport);
}
