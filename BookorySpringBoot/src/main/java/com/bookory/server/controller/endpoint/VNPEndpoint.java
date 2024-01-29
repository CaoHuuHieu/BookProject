package com.bookory.server.controller.endpoint;


import com.bookory.models.request.VnpayRequestDTO;
import com.bookory.server.constant.APIConstant;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping(APIConstant.API_VNPay)
@RestController
public interface VNPEndpoint {
    @PostMapping("/service")
    void registerService(@RequestBody VnpayRequestDTO vnpay, HttpServletRequest request);
}
