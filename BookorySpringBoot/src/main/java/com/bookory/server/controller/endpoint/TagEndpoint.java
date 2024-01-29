package com.bookory.server.controller.endpoint;

import com.bookory.entity.TagEntity;
import com.bookory.server.constant.APIConstant;
import com.bookory.server.helper.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIConstant.API_TAG)
public interface TagEndpoint {

    @GetMapping
    List<TagEntity> getAllTag();

    @GetMapping(value = "{id}")
    ResponseEntity<ResponseObject> getTagByID(@PathVariable long id);

    @PutMapping(value = "/{id}")
    TagEntity updateTag(@PathVariable long id, @RequestBody TagEntity tag);

    @PostMapping
    void addNewTag(@RequestBody TagEntity tag);

    @DeleteMapping(value = "/{id}")
    void deleteTag(@PathVariable long id);

}
