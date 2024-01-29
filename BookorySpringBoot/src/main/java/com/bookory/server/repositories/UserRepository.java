package com.bookory.server.repositories;

import java.sql.Date;

import com.bookory.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookory.entity.UserEntity;


public interface UserRepository extends JpaRepository<AccountEntity, Long> {
	AccountEntity findByEmail(String email);

	AccountEntity findByEmailAndPassword(String email, String password);

	@Query("SELECT COUNT(u) FROM UserEntity u WHERE u.role = 1")
	long countByRoleId();

	@Query("SELECT COUNT(u) FROM UserEntity u WHERE u.createDate BETWEEN :startDate AND :endDate")
	long countByCreateDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	boolean existsByEmail(String email);

}
