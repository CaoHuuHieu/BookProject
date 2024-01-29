package com.bookory.server.controller.endpoint;

import com.bookory.entity.ContactEntity;
import com.bookory.server.constant.APIConstant;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIConstant.API_CONTACT)
public interface ContactEndpoint {

    @GetMapping
    List<ContactEntity> getAllContact();

    @GetMapping(value = "{id}")
    ContactEntity getContactByID(@PathVariable long id);

    @PostMapping
    ContactEntity addNewContact(@RequestBody ContactEntity contact);

    @DeleteMapping(value = "contact/{id}")
    void deleteContact(@PathVariable long id);

    @PostMapping(value = "contact/reply")
    void replyContact(@RequestBody ContactEntity contact);

}
