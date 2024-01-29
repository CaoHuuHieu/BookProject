package com.bookory.server.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bookory.server.mapper.ServicePackConvert;
import com.bookory.models.response.ServicePackResponseDTO;
import com.bookory.entity.ServicePackEntity;
import com.bookory.server.repositories.ServicePackRepository;


@Service
public class ServicesPackServices {
	@Autowired
 	ImageStorageService storageServices;
	@Autowired
	ServicePackRepository serviceRepository;
	@Autowired
	ServicePackConvert servicePackConvert;
	public List<ServicePackResponseDTO> getAllServices() throws IOException{
		List<ServicePackEntity> servicePackEntities = serviceRepository.findAll(); 
		if(servicePackEntities.size() > 0) {
			return servicePackConvert.toServicePackResponseDTO(servicePackEntities);
		}else
			return null;
	}

	public ServicePackResponseDTO getServicesByID(long id) throws IOException{
		ServicePackEntity servicePackEntity =  serviceRepository.findById(id).orElse(null);
		if(servicePackEntity != null) {
			return servicePackConvert.toServicePackResponseDTO(servicePackEntity);
		}else 
			return null;
	}

	public ServicePackEntity addNewService(ServicePackEntity service, MultipartFile file) {
		if(file != null)
			service.setThumbnail(storageServices.storeFile(file));
		service.setStatus(0);
		return serviceRepository.save(service);
	}

	public ServicePackEntity updateService(long id, ServicePackEntity service, MultipartFile file) {
		if(file != null) {
			service.setThumbnail(storageServices.storeFile(file));
		}
		service.setId(id);
		return serviceRepository.save(service);
	}

	public ServicePackEntity deleteService(long id){
		ServicePackEntity serviceEntity = serviceRepository.findById(id).get();
		serviceEntity.setStatus(1);
		return serviceRepository.save(serviceEntity);
	}
}
