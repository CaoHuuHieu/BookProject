package com.bookory.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name="books")
@Data
@Builder
public class BookEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column(length = 255)
	private String name;

	@Column(length = 255)
	private String image;

	@Column(length = 50)
	private String author;

	@ManyToOne
	@JoinColumn(name="category_id", referencedColumnName = "id")
	private CategoryEntity categoryEntity;

	@Column(length = 50)
	private String publishing;

	@ManyToOne
	@JoinColumn(name="promotion_id", referencedColumnName = "id")
	private PromotionEntity promotionEntity;

	@Column(name="publishing_year")
	private int publishingYear;

	@ManyToOne
	@JoinColumn(name="store_id", referencedColumnName = "id")
	private StoreEntity storeEntity;

	@Column(name="page_number")
	private int pageNumber;

	private int length;

	private int width;

	private int height;

	private int weight;

	private int quantity;

	private int price;

	@Column(name="quantity_sold")
	private int quantitySold;

	@Column(name="create_date")
	private Date createDate;

	@Column(name="update_date")
	private Date updateDate;

	private int status;

	@Column(columnDefinition = "ntext")
	private String description;
}

