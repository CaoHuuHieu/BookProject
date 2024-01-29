package com.bookory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="address")
@Data
@Builder
public class AddressEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name="province_id")
	private int provinceId;

	@Column(name="district_id")
	private int districtId;

	@Column(name="ward_id")
	private int wardId;

	@Column(name="full_address", length = 300)
	private String fullAddress;

}
