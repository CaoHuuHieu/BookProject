package com.bookory.server.controller.endpoint;

import com.bookory.models.request.CartRequestDTO;
import com.bookory.models.response.CartResponseDTO;
import com.bookory.server.constant.APIConstant;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(APIConstant.API_CART)
public interface CartEndpoint {

    @GetMapping("{userid}")
    List<CartResponseDTO> getAllCartDetailByUserId(@PathVariable long userid);

    @PostMapping
    @ResponseBody
    void addNewCart(@RequestBody CartRequestDTO cart);

    @PutMapping("{id}")
    void updateAmount(@PathVariable long id, @RequestParam int amount);

    @DeleteMapping("{id}")
    void deleteCartDetail(@PathVariable long id);

    @PostMapping("pay")
    void setCartDetailStatus(@RequestBody Long[] cartIds);

    @GetMapping("pay/{userId}")
    List<CartResponseDTO> setCartDetailStatus(@PathVariable Long userId);

}
