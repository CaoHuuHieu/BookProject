package com.bookory.entity;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="accounts")
@Data
@Builder
public class AccountEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@Column(name="full_name", length = 50)
	private String fullName;

	@Column(length = 50)
	private String email;

	@Column(length = 10)
	private String phone;

	@Column(length = 200)
	private String password;

	@Column(length = 10)
	private String gender;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="address_id", referencedColumnName = "id")
	private AddressEntity addressEntity;

	@Column(name="create_date")
	private Date createDate;

	@Column(name="update_date")
	private Date updateDate;

	@Column(length = 100)
	private String avatar;

	@Column
	private int role;

	@Column
	private int status;

	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name="store_id", referencedColumnName = "id")
	private StoreEntity storeEntity;

}
