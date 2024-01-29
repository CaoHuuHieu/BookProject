package com.bookory.server.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookory.models.response.BookSold;
import com.bookory.entity.BookEntity;
import com.bookory.entity.PromotionEntity;

import jakarta.transaction.Transactional;

public interface BookRepository extends JpaRepository<BookEntity, Long>{
	List<BookEntity> findByStoreEntityId(long storeId);
	List<BookEntity> findByStoreEntityIdAndStatus(long storeId, int status);
	List<BookEntity> findByCategoryEntityIdAndStatus(long id, int status);
	List<BookEntity> findByNameContainingOrAuthorContainingOrStoreEntityNameContaining(String name, String author, String storeName);
	@Transactional
	@Modifying
	@Query("DELETE FROM BookTagEntity bte WHERE book.id = :bookId")
	void deleteBookTagsByBookId(@Param("bookId") long bookId);
	@Transactional
	@Modifying
	@Query("UPDATE BookEntity b SET b.promotionEntity = null WHERE b.promotionEntity.Id = :promotionId")
	void setPromotioneNullByPromotioneId(@Param("promotionId") long promotionId);
	@Transactional
	@Modifying
	@Query("UPDATE BookEntity b SET b.promotionEntity =:promotion  WHERE b.id IN :bookIds")
	void updatePromotionByBookIdInAndPromotionId(List<Long> bookIds, PromotionEntity promotion);
	
	@Query("SELECT b FROM BookEntity b WHERE b.storeEntity.id = :storeId AND b.promotionEntity IS NULL  AND b.status=0")
	List<BookEntity> findBooksByStoreIdAndPromotionIsNull(@Param("storeId") long storeId);
	@Query("SELECT b FROM BookEntity b "
			+ "WHERE b.storeEntity.id = :storeId AND b.status=0 AND ("
			+ "(b.promotionEntity IS NOT NULL AND NOT((b.promotionEntity.startDate between :startDate AND :endDate) OR (b.promotionEntity.endDate between :startDate AND :endDate))"
			+ "))")
	List<BookEntity> findBooksByStoreEntityIdAndDateNotBetween(@Param("storeId") long storeId,@Param("startDate") Date startDate,  @Param("endDate") Date endDate);
	@Query("SELECT b FROM BookEntity b WHERE b.storeEntity.id=:storeId AND (b.promotionEntity IS NULL or b.promotionEntity.id=:promotionId) AND b.status=0")
	List<BookEntity> findBookeByStoreEntityIdAndPromotionId(@Param("storeId") long storeId, @Param("promotionId") long promotionId);
	
	List<BookEntity> findByPromotionEntityId(long id);
	
	@Query("SELECT COUNT(b) FROM BookEntity b WHERE b.status = :status")
    long countByStatus(@Param("status") int status);
	
	 @Query("SELECT SUM(b.quantitySold) FROM BookEntity b")
    long sumQuantitySold();
	 
	 @Query("SELECT b.name, SUM(odt.amount) AS totalSold "
	            + "FROM OrderDetailEntity odt "
	            + "JOIN odt.bookEntity b "
	            + "JOIN b.storeEntity s "
	            + "JOIN odt.orderEntity o "
	            + "WHERE s.id = :storeId "
	            + "AND o.createDate BETWEEN :startDate AND :endDate "
	            + "GROUP BY b.id "
	            + "ORDER BY s.id DESC")
	    List<BookSold> findTopBooksByStoreIdAndDateRange(
	            @Param("storeId") Long storeId,
	            @Param("startDate") Date startDate,
	            @Param("endDate") Date endDate);
	List<BookEntity> findByStatus(int i);
	 
}
