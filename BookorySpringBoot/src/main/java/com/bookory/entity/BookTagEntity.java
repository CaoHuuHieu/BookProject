package com.bookory.entity;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Table(name = "book_tag")
@Data
public class BookTagEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch =  FetchType.LAZY)
	@JoinColumn(name = "book_id", referencedColumnName = "id")
	private BookEntity book;

	@ManyToOne(cascade = CascadeType.ALL,  fetch =  FetchType.LAZY)
	@JoinColumn(name = "tag_id", referencedColumnName = "id")
	private TagEntity tag;

}
