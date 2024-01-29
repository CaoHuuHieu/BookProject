package com.bookory.server.controller.endpoint;

import com.bookory.models.response.AdminReport;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@CrossOrigin
@RestController
@RequestMapping("api/report")
public interface ReportEndpoint {

    @GetMapping("/")
    AdminReport getRepost(@RequestParam Date startDate, @RequestParam Date endDate);
}
