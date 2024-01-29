package com.bookory.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="stores")
@Data
@Builder
public class StoreEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(length = 30)
	private String name;

	@Column(length = 20)
	private String phone;

	@Column(length = 50)
	private String email;

	@Column(length = 200)
	private String avatar;

	@Column(name="cover_image")
	private String coverImage;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="address_id", referencedColumnName = "id")
	private AddressEntity addressEntity;

	@Column(name="create_date")
	private LocalDateTime createDate;

	@Column(name="update_date")
	private LocalDateTime updateDate;

	@Column(name="end_date")
	private LocalDateTime endDate;

	@Column
	private int status;

}
