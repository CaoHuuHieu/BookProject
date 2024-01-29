package com.bookory.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookory.server.mapper.AddressConvert;
import com.bookory.models.request.AddressRequestDTO;
import com.bookory.models.response.AddressResponseDTO;
import com.bookory.entity.AddressEntity;
import com.bookory.entity.UserEntity;
import com.bookory.server.repositories.AddressRepository;
import com.bookory.server.repositories.UserRepository;
@Service
public class AddressServices {

	@Autowired
	AddressRepository addressRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	AddressConvert addressConvert;
	public AddressResponseDTO getAddressById(long id) {
		AddressEntity addressEntity = addressRepository.findById(id).orElse(null);
		if(addressEntity != null) {
			AddressResponseDTO address = addressConvert.toAddressResponseDTO(addressEntity);
			return address;
		}
		else
			return null;
	}
	
	public AddressEntity addNewAddress(AddressRequestDTO addressRequest) {
		AddressEntity addressEntity = addressConvert.toAddressEntity(addressRequest);
		return addressRepository.save(addressEntity);
	}
	
	public AddressEntity udpateAddress(Long id, AddressRequestDTO addressRequest) {
		AddressEntity addressEntity = addressRepository.findById(id).orElse(null);
		if(addressEntity != null) {
			addressEntity = addressConvert.toAddressEntity(addressRequest);
			addressEntity.setId(id);
			return addressRepository.save(addressEntity);
		}else {
			return null;
		}
	}

	public UserEntity addAddressForUser(Long userId, AddressRequestDTO address) {
		
		UserEntity userEntity = userRepository.findById(userId).orElse(null);
		if(userEntity != null) {
			AddressEntity addressEntity = addressConvert.toAddressEntity(address);
			addressEntity = addressRepository.save(addressEntity);
			userEntity.setAddressEntity(addressEntity);
			return userRepository.save(userEntity);
		}
		
		return null;
	}
	
}
