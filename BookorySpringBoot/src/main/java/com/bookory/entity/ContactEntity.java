package com.bookory.entity;

import java.sql.Date;
import java.time.LocalDateTime;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="contacts")
@Data
@Builder
public class ContactEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50)
	private String name;

	@Column(length = 50)
	private String gmail;

	@Column(length = 200)
	private String subject;

	@Column(columnDefinition = "ntext")
	private String content;

	@Column(name="create_date")
	private LocalDateTime createDate;

	@Column
	private int status;

}
