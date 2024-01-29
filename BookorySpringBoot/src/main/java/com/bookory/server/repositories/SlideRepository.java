package com.bookory.server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookory.entity.SlideEntity;

public interface SlideRepository extends JpaRepository<SlideEntity, Long>{

	List<SlideEntity> findByStatus(int i);

}
