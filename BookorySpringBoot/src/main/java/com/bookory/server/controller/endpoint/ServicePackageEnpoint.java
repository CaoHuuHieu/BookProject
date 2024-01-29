package com.bookory.server.controller.endpoint;

import com.bookory.entity.ServicePackEntity;
import com.bookory.models.response.ServicePackResponseDTO;
import com.bookory.server.constant.APIConstant;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(APIConstant.API_SERVICE_PACKAGE)
public interface ServicePackageEnpoint {

    @GetMapping
    @ResponseBody
    List<ServicePackResponseDTO> getAllServices();

    @GetMapping(value = "{id}")
    ServicePackResponseDTO getServicerByID(@PathVariable long id);

    @PostMapping
    ServicePackEntity addNewServicePack(@RequestParam("object") String object,
                                        @RequestParam("file") MultipartFile file);

    @PostMapping("{id}")
    void updateServicePack(@PathVariable long id,
                           @RequestParam("object") String object, @RequestParam(value = "file", required = false) MultipartFile file,
                           HttpSession session);

    @DeleteMapping(value = "{id}")
    void deleteServicePack(@PathVariable long id);

}
