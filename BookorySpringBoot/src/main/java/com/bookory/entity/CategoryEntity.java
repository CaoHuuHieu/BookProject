package com.bookory.entity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="categories")
@Data
@Builder
public class CategoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 200)
	private String name;

	@Column(length = 200)
	private String thumbnail;

	private int status;

}
