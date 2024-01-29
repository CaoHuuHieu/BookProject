package com.bookory.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="promotions")
@Data
@Builder
public class PromotionEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@Column
	private int discount;

	@Column(name="start_date")
	private LocalDateTime startDate;

	@Column(name="end_date")
	private LocalDateTime endDate;

	@Column(name="create_date")
	private LocalDateTime createDate;

	@Column(name="update_date")
	private LocalDateTime updateDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="store_id", referencedColumnName = "id")
	private StoreEntity storeEntity;

	@Column
	private int status;

}
