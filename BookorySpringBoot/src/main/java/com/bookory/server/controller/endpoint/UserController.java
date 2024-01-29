package com.bookory.server.controller.endpoint;

import com.bookory.models.request.Account;
import com.bookory.models.request.PasswordRequestDTO;
import com.bookory.models.request.UserRequestDTO;
import com.bookory.models.response.UserBasicInforDTO;
import com.bookory.models.response.UserFullInforDTO;
import com.bookory.server.constant.APIConstant;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(APIConstant.API_ACCOUNT)
public interface UserController {
    @PostMapping(value = "/checkemail")
    UserBasicInforDTO checkEmail(@RequestBody Account account);

    @PostMapping(value = "/register")
    void registerAccount(@RequestBody UserRequestDTO account);

    @PutMapping(value = "/password/update/{id}")
    void updatePassword(@PathVariable long id, @RequestBody PasswordRequestDTO pasword);

    @PutMapping(value = "/password/reset")
    void resetPassword(@RequestBody PasswordRequestDTO pasword);

    @GetMapping(value = "/otp")
    int sendOtp(@RequestParam String email);

    @PostMapping(value = "/login")
    Map<String, Object> login(@RequestBody Account account);


    @GetMapping(value = "user")
    List<UserBasicInforDTO> getAllUser();

    @GetMapping(value = "user/{id}")
    UserFullInforDTO getUserByID(@PathVariable long id);

    @GetMapping(value = "user/search")
    UserBasicInforDTO findUserByEmail(@RequestParam String key);

    @PostMapping("/user/{id}")
    void updateUser(@PathVariable long id, @RequestParam("object") String object,
                    @RequestParam(value = "file", required = false) MultipartFile file);

    @PostMapping("/user")
    void addNewUser(@RequestParam("object") String object,
                    @RequestParam(name = "file", required = false) MultipartFile file);

    @DeleteMapping(value = "user/{id}")
    void deleteUser(@PathVariable long id);
}

