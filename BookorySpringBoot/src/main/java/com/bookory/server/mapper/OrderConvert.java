package com.bookory.server.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookory.models.response.OrderResponseDTO;
import com.bookory.models.response.OrderResponseForStore;
import com.bookory.entity.OrderEntity;

@Component
public class OrderConvert {
	@Autowired 
	StoreConvert storeConvert;
	public OrderResponseForStore toOrderResponseForStore(OrderEntity orderEntity) {
		OrderResponseForStore order = new OrderResponseForStore();
		order.setId(orderEntity.getId());
		order.setName(orderEntity.getName());
		order.setPhone(orderEntity.getPhone());
		order.setAddress(orderEntity.getAddress());
		order.setCreateDate(orderEntity.getCreateDate());
		order.setPayment(orderEntity.getPayment());
		order.setStatus(orderEntity.getStatus());
		order.setTotalMoney(orderEntity.getTotalMoney());
		order.setTransportFee(orderEntity.getTransportFee());
		return order;
	}
	public List<OrderResponseForStore> toOrderResponseForStore(List<OrderEntity> orderEntities) {
		List<OrderResponseForStore> orders = new ArrayList<>();
		for(OrderEntity orderEntity: orderEntities) {
			OrderResponseForStore order = new OrderResponseForStore();
			order.setId(orderEntity.getId());
			order.setName(orderEntity.getName());
			order.setPhone(orderEntity.getPhone());
			order.setAddress(orderEntity.getAddress());
			order.setCreateDate(orderEntity.getCreateDate());
			order.setPayment(orderEntity.getPayment());
			order.setStatus(orderEntity.getStatus());
			order.setTotalMoney(orderEntity.getTotalMoney());
			order.setTransportFee(orderEntity.getTransportFee());
			orders.add(order);
		}
		return orders;
	}
	public OrderResponseDTO toOrderResponseDTO(OrderEntity orderEntity) {
		OrderResponseDTO order = new OrderResponseDTO();
		order.setId(orderEntity.getId());
		order.setStore(storeConvert.toStoreBasicInforDTO(orderEntity.getStoreEntity()));
		order.setPayment(orderEntity.getPayment());
		order.setStatus(orderEntity.getStatus());
		order.setTotalMoney(orderEntity.getTotalMoney());
		order.setTransportFee(orderEntity.getTransportFee());
		return order;
	}
}
