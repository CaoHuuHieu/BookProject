package com.bookory.server.controller.endpoint;

import com.bookory.models.request.ReviewRequestDTO;
import com.bookory.models.response.ReviewResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/")
public interface ReviewEndpoint {

    @GetMapping("/book/review/{bookId}")
    List<ReviewResponseDTO> getReviewForBook(@PathVariable("bookId") long id);

    @PostMapping("/review")
    void addReview(@RequestBody ReviewRequestDTO review);

}
