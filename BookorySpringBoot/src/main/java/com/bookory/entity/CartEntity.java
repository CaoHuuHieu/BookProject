package com.bookory.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="carts")
@Data
@Builder
public class CartEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", referencedColumnName = "id")
	private AccountEntity accountEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="book_id", referencedColumnName = "id")
	private BookEntity bookEntity;

	@Column
	private int amount;

	@Column
	private int status;

}
