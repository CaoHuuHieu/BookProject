package com.bookory.server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookory.entity.BookTagEntity;
import com.bookory.entity.TagEntity;

public interface BookTagRepository extends JpaRepository<BookTagEntity, Long>{

	@Query("SELECT tag from BookTagEntity bt where bt.book.id =:bookId")
	List<TagEntity> findByBookId(@Param("bookId") long id);
}
