package com.bookory.server.controller.endpoint;

import com.bookory.models.request.AddressRequestDTO;
import com.bookory.models.response.AddressResponseDTO;
import com.bookory.server.constant.APIConstant;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(APIConstant.API_ADDRESS)
public interface AddressEndpoint {

    @GetMapping("{addressId}")
    AddressResponseDTO getAddress(@PathVariable long addressId);

    @PutMapping("{addressId}")
    void updateAddress(@PathVariable Long addressId, @RequestBody AddressRequestDTO address);

    @PostMapping("{userId}")
    void addAddressForUser(@PathVariable Long userId, @RequestBody AddressRequestDTO address);

}
