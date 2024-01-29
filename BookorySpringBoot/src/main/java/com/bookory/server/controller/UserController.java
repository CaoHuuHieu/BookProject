package com.bookory.server.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookory.entity.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bookory.models.request.Account;
import com.bookory.models.request.PasswordRequestDTO;
import com.bookory.models.request.UserRequestDTO;
import com.bookory.models.response.UserBasicInforDTO;
import com.bookory.models.response.UserFullInforDTO;
import com.bookory.entity.UserEntity;
import com.bookory.server.helper.EmailProvider;
import com.bookory.server.helper.JwtProvider;
import com.bookory.server.helper.ResponseObject;
import com.bookory.server.services.UserServices;
import com.fasterxml.jackson.databind.ObjectMapper;
@CrossOrigin
@RestController
@RequestMapping("/api/")
public class UserController {
	@Autowired
	EmailProvider emailProvider;
	@Autowired
	UserServices userServices;
	@Autowired
	JwtProvider jwtProvider;
	@PostMapping(value = "/checkemail")
	@ResponseBody
	public ResponseEntity<ResponseObject> checkEmail(@RequestBody Account account) {
		try {
			UserBasicInforDTO userResponse = userServices.findUserByEmail(account.getEmail());
			if (userResponse != null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Tài khoản đã tồn tại, vui lòng chọn Đăng nhập!", account));
			} else {
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(404, "Tài khoản không tồn tại!", null));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", null));
		}
	}
	
	@PostMapping(value = "/register")
	@ResponseBody
	public ResponseEntity<ResponseObject> registerAccount(@RequestBody UserRequestDTO account) {
		try {
			userServices.registerAccount(account);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Tài khoản này đã tồn tại!", ""));
		}
	}
	@PutMapping(value = "/password/update/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> updatePassword(@PathVariable long id, @RequestBody PasswordRequestDTO pasword) {
		try {
			if(userServices.updatePasword(id, pasword))
				return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(500, "Mật khẩu không chính xác!", false));
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject(404, "Tài khoản không tồn tại!", ""));
		}
	}
	@PutMapping(value = "/password/reset")
	@ResponseBody
	public ResponseEntity<ResponseObject> resetPassword(@RequestBody PasswordRequestDTO pasword) {
		try {
			if(userServices.resetPassword(pasword))
				return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", true));
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(500, "Mật khẩu không chính xác!", false));
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject(404, "Tài khoản không tồn tại!", ""));
		}
	}
	@GetMapping(value = "/otp")
	@ResponseBody
	public ResponseEntity<ResponseObject> sendOtp(@RequestParam String email) {
		try {
			int randomPin = (int) (Math.random() * 9000) + 1000;
			String subject = "BookStore Send OTP";
			String body = "Chào bạn, mã xác thực của bạn là: " + randomPin + ".";
			emailProvider.sendEmail(email, subject, body);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Đã gửi otp qua email!", randomPin));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}

	@PostMapping(value = "/login")
	@ResponseBody
	public ResponseEntity<ResponseObject> login(@RequestBody Account account) {
		try {
			UserBasicInforDTO user = userServices.checkLogIn(account.getEmail(), account.getPassword());
			Map<String, Object> response = new HashMap<String, Object>();
			if (user != null) {
				String accessToken = jwtProvider.createToken(account.getEmail());
				response.put("user", user);
				response.put("accessToken", accessToken);
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Tài khoản, mật khẩu hợp lệ", response));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Tài khoản hoặc mật khẩu không chính xác!", ""));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject(500, "Thao tác không được thực hiện", ""));
		}
	}

	
	@GetMapping(value = "user")
	@ResponseBody
	public ResponseEntity<ResponseObject> getAllUser() {
		try {
			List<UserBasicInforDTO> userBasicInforDTOs = userServices.getAllUser();
			if (userBasicInforDTOs!= null)
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thực hiện thành công!", userBasicInforDTOs));
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject(404, "Không tìm thấy dữ liệu", ""));
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện", ""));
		}

	}

	@GetMapping(value = "user/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> getUserByID(@PathVariable long id) {
		try {
			UserFullInforDTO userFullInforDTO = userServices.getUserByID(id);
			if (userFullInforDTO != null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thực hiện thành công!", userFullInforDTO));
			} else
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(505, "Thao tác không được thực hiện!", ""));
		}
	}

	@GetMapping(value = "user/search")
	@ResponseBody
	public ResponseEntity<ResponseObject> findUserByEmail(@RequestParam String key) {
		try {
			UserBasicInforDTO userBasicInforDTO = userServices.findUserByEmail(key);
			if (userBasicInforDTO != null) {
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject(200, "Thao tác thực hiện thành công!", userBasicInforDTO));
			} else
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject(404, "Không tìm thấy dữ liệu!", ""));
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(505, "Thao tác không được thực hiện!", ""));
		}
	}

	@PostMapping("/user/{id}")
	public ResponseEntity<ResponseObject> udpateUser(@PathVariable long id, @RequestParam("object") String object,
			@RequestParam(value = "file", required = false) MultipartFile file) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			UserRequestDTO user = null;
			user = mapper.readValue(object, UserRequestDTO.class);
			UserEntity userEntity = userServices.updateUser(id, user, file);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", userEntity));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
		}
	}

	@PostMapping("/user")
	public ResponseEntity<ResponseObject> addNewUser(@RequestParam("object") String object,
			@RequestParam(name = "file", required = false) MultipartFile file) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			UserRequestDTO user = null;
			user = mapper.readValue(object, UserRequestDTO.class);
			AccountEntity userEntity = userServices.addNewUser(user, file);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", userEntity));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new ResponseObject(409, "Tài khoản này đã tồn tại!", ""));
		}
	}
	@DeleteMapping(value = "user/{id}")
	@ResponseBody
	public ResponseEntity<ResponseObject> deleteUser(@PathVariable long id) {
		AccountEntity userEntity = userServices.deleteUser(id);
		if (userEntity != null)
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(200, "Thao tác thực hiện thành công!", userEntity));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseObject(500, "Thao tác không được thực hiện!", ""));
	}
	
	
	
}

