package com.bookory.server.controller.endpoint;


import com.bookory.models.request.OrderRequestDTO;
import com.bookory.models.response.OrderResponseDTO;
import com.bookory.models.response.OrderResponseForStore;
import com.bookory.server.constant.APIConstant;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(APIConstant.API_ORDER)
public interface OrderEndpoint {

    @GetMapping("store/orders/{storeId}")
    List<OrderResponseForStore> getOrdersByStoreId(@PathVariable long storeId);

    @GetMapping("store/order/{id}")
    OrderResponseForStore getOrdersById(@PathVariable long id);

    @PostMapping
    void addNewOrder(@RequestBody OrderRequestDTO order);

    @GetMapping("{userId}")
    List<OrderResponseDTO> getOrders(@PathVariable long userId);

    @PutMapping("{id}/{status}")
    void udpateOrderStatus(@PathVariable long id, @PathVariable int status);
}
