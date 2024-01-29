package com.bookory.server.services;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.bookory.entity.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bookory.server.mapper.UserConvert;
import com.bookory.models.request.PasswordRequestDTO;
import com.bookory.models.request.UserRequestDTO;
import com.bookory.models.response.UserBasicInforDTO;
import com.bookory.models.response.UserFullInforDTO;
import com.bookory.entity.UserEntity;
import com.bookory.server.repositories.UserRepository;
@Service
public class UserServices {
	@Autowired
	UserRepository userRepository;
	@Autowired
	AddressServices addressServices;
	@Autowired
	UserConvert userConvert;
	@Autowired
	ImageStorageService storageServices;
	@Autowired
	private PasswordEncoder passwordEncoder;
	public List<UserBasicInforDTO> getAllUser() throws IOException{
		List<UserEntity> userEntities = userRepository.findAll();
		if(userEntities.size() > 0)
			return userConvert.toUserBasicInforDTOs(userEntities);
		else 
			return null;
	}
	public UserFullInforDTO getUserByID(long id) throws IOException{
		UserEntity userEntity = userRepository.findById(id).orElse(null);
		if(userEntity != null) {
			return userConvert.toUserFullInforDTO(userEntity);
		}else
			return null;
	}
	
	public AccountEntity addNewUser(UserRequestDTO user, MultipartFile file) throws Exception{
		if(!userRepository.existsByEmail(user.getEmail())) {
			AccountEntity userEntity = userConvert.toUserEntity(user);
			if(file != null) {
				String fileName = storageServices.storeFile(file);
				userEntity.setAvatar(fileName);
			}
			if(user.getAddress() != null)
				userEntity.setAddressEntity(addressServices.addNewAddress(user.getAddress()));
			else
				userEntity.setAddressEntity(null);
			userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
			userEntity.setCreateDate(Date.valueOf(LocalDate.now()));	
			userEntity.setStatus(0);
			return userRepository.save(userEntity);
		}else {
			throw new Exception("Tài khoản này đã tồn tại!");
		}
	}
	public UserEntity registerAccount(UserRequestDTO user) throws Exception {
		if(!userRepository.existsByEmail(user.getEmail())) {	
			UserEntity userEntity = new UserEntity();
			userEntity.setFullName(user.getFullName());
			userEntity.setEmail(user.getEmail());
			userEntity.setRole(0);
			userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
			userEntity.setCreateDate(Date.valueOf(LocalDate.now()));	
			userEntity.setStatus(0);
			return userRepository.save(userEntity);
		}else {
			throw new Exception("Tài khoản này đã tồn tại!");
		}
	}
	public UserEntity registerAccount(UserRequestDTO account, MultipartFile file) throws Exception{
		if(userRepository.existsByEmail(account.getEmail())) {	
			UserEntity userEntity = userConvert.toUserEntity(account);
			userEntity.setPassword(passwordEncoder.encode(account.getPassword()));
			userEntity.setCreateDate(Date.valueOf(LocalDate.now()));	
			userEntity.setStatus(0);
			return userRepository.save(userEntity);
		}else {
			throw new Exception("Tài khoản này đã tồn tại!");
		}
		
	}
	public UserEntity updateUser(long id, UserRequestDTO user, MultipartFile file) throws IOException{
		UserEntity userEntity = userRepository.findById(id).orElse(null);
		if(userEntity != null) {
			userEntity = userConvert.toUserEntity(user);
			if (user.getStoreId() == 0) 
				userEntity.setStoreEntity(null);
			userEntity.setId(id);
			String password = userRepository.findById(id).get().getPassword();
			userEntity.setPassword(password);
			userEntity.setUpdateDate(Date.valueOf(LocalDate.now()));
			if(file != null) {
				String avatarPath = storageServices.storeFile(file);
				userEntity.setAvatar(avatarPath);
			}
			return userRepository.save(userEntity);
		}else
			return null;
	}	
	
	public UserBasicInforDTO findUserByEmail(String key) throws IOException {
		AccountEntity userEntity = userRepository.findByEmail(key);
		
		if(userEntity != null) {
			UserBasicInforDTO userBasicInforDTO = userConvert.toUserBasicInforDTO(userEntity);
			return userBasicInforDTO;
		}else
			return null;
	}	
	public AccountEntity deleteUser(long id) {
		AccountEntity userEntity = userRepository.findById(id).get();
		userEntity.setStatus(1);
		return userRepository.save(userEntity);
	}
	
	 public UserBasicInforDTO checkLogIn(String email, String password) throws IOException {
		 AccountEntity userEntity = userRepository.findByEmail(email);
	        if (userEntity != null && passwordEncoder.matches(password, userEntity.getPassword())) {
	            return userConvert.toUserBasicInforDTO(userEntity);
	        } else {
	            return null;
	        }
	    }
	public boolean updatePasword(long id, PasswordRequestDTO passwordRequestDTO){
		UserEntity userEntity = userRepository.findById(id).orElse(null);
		if(userEntity != null) {
			if(passwordEncoder.matches(passwordRequestDTO.getOldPassword(), userEntity.getPassword())) {
				userEntity.setPassword(passwordEncoder.encode(passwordRequestDTO.getNewPassword()));
				userRepository.save(userEntity);
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	public boolean resetPassword(PasswordRequestDTO passwordRequestDTO) {
		UserEntity userEntity = userRepository.findByEmail(passwordRequestDTO.getEmail());
		if(userEntity != null) {
			
			userEntity.setPassword(passwordEncoder.encode(passwordRequestDTO.getNewPassword()));
			userRepository.save(userEntity);
			return true;
		}else {
			return false;
		}
	}
}
