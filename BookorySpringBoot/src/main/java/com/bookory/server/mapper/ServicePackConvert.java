package com.bookory.server.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bookory.models.response.ServicePackResponseDTO;
import com.bookory.entity.ServicePackEntity;

@Component
public class ServicePackConvert {

	public ServicePackResponseDTO toServicePackResponseDTO(ServicePackEntity servicePackEntity) {
		ServicePackResponseDTO servicePack = new ServicePackResponseDTO();
		servicePack.setId(servicePackEntity.getId());
		servicePack.setName(servicePackEntity.getName());
		servicePack.setPrice(servicePackEntity.getPrice());
		servicePack.setExpirationDate(servicePackEntity.getExpirationDate());
		servicePack.setThumbnail(servicePackEntity.getThumbnail());
		servicePack.setDescription(servicePackEntity.getDescription());
		servicePack.setStatus(servicePackEntity.getStatus());
		return servicePack;
	}
	public List<ServicePackResponseDTO> toServicePackResponseDTO(List<ServicePackEntity> servicePackEntities) {
		List<ServicePackResponseDTO>  servicePacks = new ArrayList<>();
		for(ServicePackEntity servicePackEntity: servicePackEntities) {
			servicePacks.add(toServicePackResponseDTO( servicePackEntity));
		}
		return servicePacks;
	}

}
