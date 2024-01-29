package com.bookory.server.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookory.entity.BookEntity;
import com.bookory.entity.OrderDetailEntity;


public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long>{

	List<OrderDetailEntity> findByOrderEntityId(long id);

	
}
