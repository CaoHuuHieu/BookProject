package com.bookory.server.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookory.models.request.UserRequestDTO;
import com.bookory.models.response.UserBasicInforDTO;
import com.bookory.models.response.UserFullInforDTO;
import com.bookory.entity.UserEntity;
import com.bookory.server.repositories.AddressRepository;
import com.bookory.server.repositories.StoreRepository;

@Component
public class UserConvert {
	@Autowired
	AddressConvert addressConvert;
	@Autowired
	StoreRepository storeRepository;
	@Autowired
	AddressRepository addressRepository;
	public List<UserBasicInforDTO> toUserBasicInforDTOs(List<UserEntity> userEntities) {
		 List<UserBasicInforDTO> users = new ArrayList<>();
		 for(UserEntity userEntity:userEntities) {
			 users.add(toUserBasicInforDTO(userEntity));
		 }
		return users;
	}

	public UserFullInforDTO toUserFullInforDTO(UserEntity userEntity) {
		UserFullInforDTO user = new UserFullInforDTO();
		user.setId(userEntity.getId());
		user.setFullName(userEntity.getFullName());
		user.setEmail(userEntity.getEmail());
		user.setPhone(userEntity.getPhone());
		user.setGender(userEntity.getGender());
		user.setRole(userEntity.getRole());
		user.setCreateDate(userEntity.getCreateDate());
		user.setUpdateDate(userEntity.getUpdateDate());
		user.setStatus(userEntity.getStatus());
		user.setStoreId((userEntity.getStoreEntity() == null)?0:userEntity.getStoreEntity().getId());
		user.setAvatar(userEntity.getAvatar());
		if(userEntity.getAddressEntity() != null)
			user.setAddress(addressConvert.toAddressResponseDTO(userEntity.getAddressEntity()));
		else
			user.setAddress(null);
		return user;
	}

	public UserEntity toUserEntity(UserRequestDTO user) {
		UserEntity userEntity = new UserEntity();
		userEntity.setFullName(user.getFullName());
		userEntity.setEmail(user.getEmail());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());
		userEntity.setCreateDate(user.getCreateDate());
		userEntity.setUpdateDate(user.getUpdateDate());
		userEntity.setAvatar(user.getAvatar());
		userEntity.setRole(user.getRole());
		userEntity.setStatus(user.getStatus());
		if(user.getStoreId() != 0)
			userEntity.setStoreEntity(storeRepository.findById(user.getStoreId()).orElse(null));
		if(user.getAddress() != null)
			userEntity.setAddressEntity(addressRepository.findById(user.getAddress().getId()).orElse(null));
		return userEntity;
	}

	public UserBasicInforDTO toUserBasicInforDTO(UserEntity userEntity) {
		UserBasicInforDTO user = new UserBasicInforDTO();
		user.setId(userEntity.getId());
		user.setFullName(userEntity.getFullName());
		user.setEmail(userEntity.getEmail());
		user.setPhone(userEntity.getPhone());
		user.setGender(userEntity.getGender());
		user.setRole(userEntity.getRole());
		user.setStatus(userEntity.getStatus());
		user.setStoreId((userEntity.getStoreEntity() == null)?0:userEntity.getStoreEntity().getId());
		user.setAvatar(userEntity.getAvatar());
		if(userEntity.getAddressEntity() != null)
			user.setAddress(addressConvert.toAddressResponseDTO(userEntity.getAddressEntity()));
		else
			user.setAddress(null);
		return user;
	}

}
