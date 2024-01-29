package com.bookory.server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookory.entity.ReviewEntity;


public interface ReviewRepository extends JpaRepository<ReviewEntity, Long>{
	List<ReviewEntity> findByBookEntityId( long bookId);

}
