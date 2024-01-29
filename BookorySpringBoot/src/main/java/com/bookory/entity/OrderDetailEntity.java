package com.bookory.entity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="order_details")
@Data
@NoArgsConstructor
@Builder
public class OrderDetailEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "id", name="order_id")
	private OrderEntity orderEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "id", name="book_id")
	private BookEntity bookEntity;

	@Column
	private int amount;
	@Column
	private int price;

	@Column
	private Integer discount;

}
