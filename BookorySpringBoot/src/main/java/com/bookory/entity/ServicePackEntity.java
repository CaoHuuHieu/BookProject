package com.bookory.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="service_packages")
@Data
@Builder
public class ServicePackEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 200)
	private String name;

	@Column
	private int price;

	@Column(name="expiration_date")
	private int expirationDate;

	@Column
	private String thumbnail;

	@Column
	private String description;

	@Column
	private int status;
}
