package com.bookory.server.controller.endpoint;

import com.bookory.entity.PromotionEntity;
import com.bookory.models.request.PromotionRequestDTO;
import com.bookory.models.response.BookBasicInfoDTO;
import com.bookory.server.constant.APIConstant;
import com.bookory.server.helper.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(APIConstant.API_PROMOTION)
public interface PromotionEndpoint {

    @GetMapping(value = "{id}")
    ResponseEntity<ResponseObject> getAllPromotionByStoreID(@PathVariable long id);

    @GetMapping(value = "promotions/book/{storeId}")
    List<BookBasicInfoDTO> getProductForPromotion(@PathVariable("storeId") long storeId,
                                                  @RequestParam("startDate") Date startDate,
                                                  @RequestParam("endDate") Date endDate);

    @GetMapping(value = "book/{storeId}/{promotionId}")
    List<BookBasicInfoDTO> getProductForUpdatePromotion(@PathVariable("storeId") long storeId,
                                                        @PathVariable("promotionId") long promotionId);

    @GetMapping(value = "{id}")
    PromotionEntity getPromotionByID(@PathVariable long id);

    @PostMapping
    void addNewPromotion(@RequestBody PromotionRequestDTO promotion);

    @DeleteMapping(value = "{id}")
    void deletePromotion(@PathVariable long id);

    @PutMapping(value = "{id}")
    void updatePromotion(@PathVariable Long id,
                         @RequestBody PromotionRequestDTO promotion);

    @PutMapping(value = "{id}/{status}")
    void updatePromotionStatus(@PathVariable Long id, @PathVariable int status);
}
