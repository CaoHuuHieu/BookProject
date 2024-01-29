package com.bookory.server.controller.endpoint;

import com.bookory.entity.CategoryEntity;
import com.bookory.server.constant.APIConstant;
import com.bookory.server.helper.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(APIConstant.API_CATEGORY)
public interface CategoryEndpoint {
    @GetMapping
    List<CategoryEntity> getAllCategory();

    @GetMapping(value = "{id}")
    CategoryEntity getCategoryByID(@PathVariable long id);

    @PutMapping(value = "{id}")
    ResponseEntity<ResponseObject> updateCategory(@PathVariable long id, @RequestParam("object") String object,
                                                  @RequestParam(name = "file", required = false) MultipartFile file);

    @PostMapping
    void addNewCategory(@RequestParam("object") String object, @RequestParam("file") MultipartFile file);

    @DeleteMapping(value = "{id}")
    void deleteCategory(@PathVariable long id);

}
