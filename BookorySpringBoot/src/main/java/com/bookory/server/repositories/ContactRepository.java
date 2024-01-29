package com.bookory.server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookory.entity.ContactEntity;



public interface ContactRepository extends JpaRepository<ContactEntity, Long>{
	List<ContactEntity> findByStatus(int status);
}
