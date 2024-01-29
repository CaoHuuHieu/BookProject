package com.bookory.server.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookory.models.request.StoreRequestDTO;
import com.bookory.models.response.StoreBasicInforDTO;
import com.bookory.models.response.StoreResponseDTO;
import com.bookory.entity.StoreEntity;
import com.bookory.server.repositories.AddressRepository;
@Component
public class StoreConvert {
	@Autowired 
	AddressConvert addressConvert;
	@Autowired 
	AddressRepository addressRepository;

	public StoreResponseDTO toStoreResponseDTO(StoreEntity store) {
		StoreResponseDTO storeResponse = new StoreResponseDTO();
		storeResponse.setId(store.getId());
		storeResponse.setName(store.getName());
		storeResponse.setPhone(store.getPhone());
		storeResponse.setEmail(store.getEmail());
		storeResponse.setAvatar(store.getAvatar());
		storeResponse.setCoverImage(store.getCoverImage());
		storeResponse.setEndDate(store.getEndDate());
		storeResponse.setAddress((store.getAddressEntity() == null)?null:addressConvert.toAddressResponseDTO(store.getAddressEntity()));
		storeResponse.setCreateDate(store.getCreateDate());
		storeResponse.setUpdateDate(store.getUpdateDate());
		storeResponse.setStatus(store.getStatus());
		return storeResponse;
	}
	public StoreEntity toStoreEntity(StoreRequestDTO store) {
		StoreEntity storeEntity = new StoreEntity();
		storeEntity.setName(store.getName());
		storeEntity.setPhone(store.getPhone());
		storeEntity.setEmail(store.getEmail());
		storeEntity.setAvatar(store.getAvatar());
		storeEntity.setCoverImage(store.getCoverImage());
		storeEntity.setEndDate(store.getEndDate());
		
		if(store.getAddress().getId() != 0)
			storeEntity.setAddressEntity(addressRepository.findById(store.getAddress().getId()).orElse(null));
		storeEntity.setCreateDate(store.getCreateDate());
		storeEntity.setUpdateDate(store.getUpdateDate());
		storeEntity.setStatus(store.getStatus());
		return storeEntity;
	}
	
	public StoreBasicInforDTO toStoreBasicInforDTO(StoreEntity storeEntity) {
		StoreBasicInforDTO store = new StoreBasicInforDTO();
		store.setId(storeEntity.getId());
		store.setName(storeEntity.getName());
		store.setAvatar(storeEntity.getAvatar());
		store.setAddress(addressConvert.toAddressResponseDTO(storeEntity.getAddressEntity()));
		return store;
	}
}
