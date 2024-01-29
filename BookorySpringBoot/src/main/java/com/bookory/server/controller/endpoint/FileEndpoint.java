package com.bookory.server.controller.endpoint;


import org.apache.catalina.webresources.FileResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@Controller
@RequestMapping(path = "/api/")
public interface FileEndpoint {

    @PostMapping("")
    void uploadFile(@RequestParam("file") MultipartFile file);

    @GetMapping("/image/{fileName:.+}")
    FileResource readDetailFile(@PathVariable String fileName);

}
