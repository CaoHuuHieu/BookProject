package com.bookory.server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookory.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
	List<CategoryEntity> findByStatus(int status);
}
