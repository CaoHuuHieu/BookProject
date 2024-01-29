package com.bookory.server.services;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bookory.server.mapper.StoreConvert;
import com.bookory.models.request.StoreRequestDTO;
import com.bookory.models.response.StoreResponseDTO;
import com.bookory.entity.AddressEntity;
import com.bookory.entity.ServicePackEntity;
import com.bookory.entity.StoreEntity;
import com.bookory.entity.UserEntity;
import com.bookory.server.repositories.ServicePackRepository;
import com.bookory.server.repositories.StoreRepository;
import com.bookory.server.repositories.UserRepository;

@Service
public class StoreServices {
	@Autowired
	private StoreRepository storeRepository;
	@Autowired
	ImageStorageService	storageServices;
	@Autowired
	UserRepository userRepository;
	@Autowired
	AddressServices addressServices;
	@Autowired
	ServicePackRepository servicesPackRepository;
	@Autowired
	StoreConvert storeConvert;
	
	public List<StoreResponseDTO> getAllStore() throws IOException{
		List<StoreEntity> stores = storeRepository.findAll();
		List<StoreResponseDTO> storeResponseDTOs = new ArrayList<StoreResponseDTO>();
		StoreResponseDTO storeResponseDTO;
		for(StoreEntity store:stores) {
			storeResponseDTO = storeConvert.toStoreResponseDTO(store);
			storeResponseDTOs.add(storeResponseDTO);
		}
		return storeResponseDTOs;
	}
	
	public StoreResponseDTO getStoreByID(long id) throws IOException{
		StoreEntity store = storeRepository.findById(id).orElse(null);
		if(store != null) {
			StoreResponseDTO storeResponseDTO = storeConvert.toStoreResponseDTO(store);
			return storeResponseDTO;
		}
		else 
			return null;
	}

	public StoreEntity addNewStore(StoreRequestDTO store, MultipartFile avatar, MultipartFile coverImage) throws IOException{
		StoreEntity storeEntity = storeConvert.toStoreEntity(store);
		if (avatar != null) {
			String avatarPath = storageServices.storeFile(avatar);
			storeEntity.setAvatar(avatarPath);
		}
		if (coverImage != null) {
			String coverImagePath = storageServices.storeFile(coverImage);
			storeEntity.setCoverImage(coverImagePath);
		}
		AddressEntity addressEntity = addressServices.addNewAddress(store.getAddress());
		storeEntity.setAddressEntity(addressEntity);
		storeEntity.setCreateDate(Date.valueOf(LocalDate.now()));
		storeEntity.setStatus(0);
		storeEntity.setEndDate(Date.valueOf(LocalDate.now()));
		UserEntity userEntity = userRepository.findById(store.getUserId()).get();
		userEntity.setStoreEntity(storeEntity);
		userRepository.save(userEntity);
		return storeEntity;
	}
	
	public StoreEntity udpateStore(long id, StoreRequestDTO store, MultipartFile avatar, MultipartFile coverImage) throws IOException{
		StoreEntity storeEntity = storeRepository.findById(id).orElse(null);
		if(storeEntity != null) {
			storeEntity = storeConvert.toStoreEntity(store);
			if (avatar != null) {
				String avatarPath = storageServices.storeFile(avatar);
				storeEntity.setAvatar(avatarPath);
			}
			if (coverImage != null) {
				String coverImagePath = storageServices.storeFile(coverImage);
				storeEntity.setCoverImage(coverImagePath);
			}
			storeEntity.setId(id);
			storeEntity.setUpdateDate(Date.valueOf(LocalDate.now()));
			return storeRepository.save(storeEntity);
		}else 
			return null;
	}
	
	public StoreEntity deleteStore(long id){
		StoreEntity storeEntity = storeRepository.findById(id).orElse(null);
		if(storeEntity != null) {
			storeEntity.setStatus(1);
			return storeRepository.save(storeEntity);
		}else
			return null;
		
	}
	public StoreEntity updateStoreEndDate(long storeId, long serviceId) {
		StoreEntity storeEntity = storeRepository.findById(storeId).orElse(null);
		ServicePackEntity servicePack = servicesPackRepository.findById(serviceId).orElse(null);
		if(storeEntity != null && servicePack != null) {
		
			int numDay = servicePack.getExpirationDate();
			Date sqlDate = Date.valueOf(LocalDate.now());
	        long millis = sqlDate.getTime();
	        long millisToAdd = numDay * 24L * 60L * 60L * 1000L;
	        long millisAfterAddingDays = millis + millisToAdd;
	        Date dateAfterAddingDays = new Date(millisAfterAddingDays);
	        storeEntity.setEndDate(dateAfterAddingDays);
	        return storeRepository.save(storeEntity);
		}else {
			return null;
		}
		
	}
}
