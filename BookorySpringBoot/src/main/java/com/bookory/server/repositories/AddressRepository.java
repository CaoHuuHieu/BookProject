package com.bookory.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookory.entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Long>{
	
}
