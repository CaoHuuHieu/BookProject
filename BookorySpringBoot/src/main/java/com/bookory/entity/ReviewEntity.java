package com.bookory.entity;
import java.sql.Date;
import java.time.LocalDateTime;

import com.bookory.models.request.Account;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="reviews")
@Data
@Builder
public class ReviewEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "id", name="user_id")
	private AccountEntity accountEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "id", name="book_id")
	private BookEntity bookEntity;

	@Column
	private int star;

	@Column
	private String comment;

	@Column(name="create_date")
	private LocalDateTime createDate;

}
