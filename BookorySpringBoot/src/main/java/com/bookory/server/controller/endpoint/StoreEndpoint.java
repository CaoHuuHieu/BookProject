package com.bookory.server.controller.endpoint;

import com.bookory.models.response.StoreResponseDTO;
import com.bookory.server.constant.APIConstant;
import com.bookory.server.helper.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(APIConstant.API_STORE)
public interface StoreEndpoint {

    @GetMapping
    List<StoreResponseDTO> getAllStore();

    @GetMapping(value = "/{id}")
    StoreResponseDTO getStoreByID(@PathVariable long id);


    @PostMapping
    void addNewStore(@RequestParam("object") String object,
                     @RequestParam(name = "avatar", required = false) MultipartFile avatar,
                     @RequestParam(name = "coverimage", required = false) MultipartFile coverimage);

    @PostMapping("store/{storeId}/{serviceId}")
    void updateStoreEndDate(@PathVariable long storeId, @PathVariable long serviceId);

    @PostMapping("store/{id}")
    void updateStore(@PathVariable long id, @RequestParam("object") String object,
                     @RequestParam(name = "avatar", required = false) MultipartFile avatar,
                     @RequestParam(name = "coverimage", required = false) MultipartFile coverimage);

    @DeleteMapping(value = "/{id}")
    ResponseEntity<ResponseObject> lockStore(@PathVariable long id);

}
