package com.bookory.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookory.entity.TagEntity;


public interface TagRepository extends JpaRepository<TagEntity, Long>{

}
