package com.bookory.server.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookory.entity.OrderEntity;


public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
	
	List<OrderEntity> findByStoreEntityId(long storeId);
	List<OrderEntity> findByStoreEntityIdAndStatus(long storeId, int status);
	List<OrderEntity> findByUserEntityId(long userId);
	
	 @Query("SELECT SUM(o.totalMoney) FROM OrderEntity o")
     long sumTotalMoney();
	 @Query("SELECT SUM(o.totalMoney) FROM OrderEntity o WHERE o.createDate BETWEEN :startDate AND :endDate")
	 long sumTotalMoneyBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	 @Query("SELECT COUNT(o.id) FROM OrderEntity o WHERE o.createDate BETWEEN :startDate AND :endDate")
	 long countByCreateDateBetween(Date startDate, Date endDate);
	 
//	  @Query("SELECT MONTH(o.createDate) as month, SUM(o.totalMoney) as revenue FROM Order o WHERE MONTH(o.createDate) = MONTH(:startDate) AND DAY(o.orderDate) >= DAY(:startDate) AND DAY(o.createDate) <= DAY(:endDate) GROUP BY MONTH(o.orderDate)")
//	  List<MonthlyRevenue> calculateMonthlyRevenueBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
	
}
