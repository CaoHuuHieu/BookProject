package com.bookory.entity;

import java.time.LocalDateTime;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="orders")
@Data
@Builder
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", referencedColumnName = "id")
	private AccountEntity accountEntity;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="store_id", referencedColumnName = "id")
	private StoreEntity storeEntity;

	@Column
	private String name;

	@Column
	private String phone;

	@Column
	private String address;

	@Column(name="create_date")
	private LocalDateTime createDate;

	@Column
	private String note;

	@Column
	private int status;

	@Column
	private int payment;

	@Column(name="total_money")
	private long totalMoney;

	@Column(name="transport_fee")
	private long transportFee;

}
