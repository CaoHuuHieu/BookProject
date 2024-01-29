package com.bookory.server.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bookory.models.response.OrderDetailResponseDTO;
import com.bookory.entity.OrderDetailEntity;

@Component
public class OrderDetailConvert {
	public OrderDetailResponseDTO toOrderDetailResponseDTO(OrderDetailEntity orderDetailEntity) {
		OrderDetailResponseDTO orderDetail = new OrderDetailResponseDTO();
		orderDetail.setId(orderDetailEntity.getId());
		orderDetail.setBookId(orderDetailEntity.getBookEntity().getId());
		orderDetail.setImage(orderDetailEntity.getBookEntity().getImage().split(",")[0]);
		orderDetail.setName(orderDetailEntity.getBookEntity().getName());
		orderDetail.setAuthor(orderDetailEntity.getBookEntity().getAuthor());
		orderDetail.setPrice(orderDetailEntity.getPrice());
		orderDetail.setDiscount(orderDetailEntity.getDiscount());
		orderDetail.setAmount(orderDetailEntity.getAmount());
		
		return orderDetail;
	}
	public List<OrderDetailResponseDTO> toOrderDetailResponseDTO(List<OrderDetailEntity> orderDetailEntities) {
		List<OrderDetailResponseDTO>  orderDetails = new ArrayList<>();
		for(OrderDetailEntity orderDetailEntity:orderDetailEntities) {
			OrderDetailResponseDTO orderDetail = toOrderDetailResponseDTO(orderDetailEntity);
			orderDetails.add(orderDetail);
		}
		return orderDetails;
	}
	
}
