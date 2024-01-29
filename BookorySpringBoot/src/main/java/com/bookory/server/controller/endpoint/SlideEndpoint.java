package com.bookory.server.controller.endpoint;

import com.bookory.entity.SlideEntity;
import com.bookory.server.constant.APIConstant;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(APIConstant.API_SLIDE)
public interface SlideEndpoint {

    @GetMapping
    List<SlideEntity> getAllSlide();

    @GetMapping("/show")
    List<SlideEntity> getSlideVisible();

    @PutMapping("/{id}")
    void setSlideVisible(@PathVariable long id);

    @PostMapping
    void addNewSlide(@RequestParam MultipartFile file);

    @DeleteMapping("/{id}")
    void deleteSlide(@PathVariable long id);

}
