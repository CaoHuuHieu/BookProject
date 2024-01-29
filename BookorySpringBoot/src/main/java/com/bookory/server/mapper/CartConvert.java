package com.bookory.server.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookory.models.request.CartRequestDTO;
import com.bookory.models.response.CartDetailDTO;
import com.bookory.entity.CartEntity;
import com.bookory.server.repositories.BookRepository;
import com.bookory.server.repositories.UserRepository;

@Component
public class CartConvert {
	@Autowired
	StoreConvert storeConvert;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	UserRepository userRepository;
	public CartDetailDTO toCartDetailDTO(CartEntity cartEntity) {
		CartDetailDTO cart = new CartDetailDTO();
		cart.setId(cartEntity.getId());
		cart.setBookId(cartEntity.getBookEntity().getId());
		cart.setImage(cartEntity.getBookEntity().getImage().split(",")[0]);
		cart.setName(cartEntity.getBookEntity().getName());
		cart.setAuthor(cartEntity.getBookEntity().getAuthor());
		cart.setPrice(cartEntity.getBookEntity().getPrice());
		cart.setDiscount((cartEntity.getBookEntity().getPromotionEntity() == null)?0:cartEntity.getBookEntity().getPromotionEntity().getDiscount());
		cart.setAmount(cartEntity.getAmount());
		return cart;
	}
	public List<CartDetailDTO> toCartDetailDTO(List<CartEntity> cartEntities) {
		List<CartDetailDTO> cartDetails = new ArrayList<>();
		for(CartEntity cartEntity:cartEntities) {
			CartDetailDTO cart = new CartDetailDTO();
			cart.setId(cartEntity.getId());
			cart.setBookId(cartEntity.getBookEntity().getId());
			cart.setImage(cartEntity.getBookEntity().getImage().split(",")[0]);
			cart.setName(cartEntity.getBookEntity().getName());
			cart.setAuthor(cartEntity.getBookEntity().getAuthor());
			cart.setPrice(cartEntity.getBookEntity().getPrice());
			cart.setDiscount((cartEntity.getBookEntity().getPromotionEntity() == null)?0:cartEntity.getBookEntity().getPromotionEntity().getDiscount());
			cart.setAmount(cartEntity.getAmount());
			cartDetails.add(cart);
		}
		return cartDetails;
	}
	public CartEntity toCartEntity(CartRequestDTO cart) {
		CartEntity cartEntity = new CartEntity();
		cartEntity.setBookEntity(bookRepository.findById(cart.getBookId()).orElse(null));
		cartEntity.setUserEntity(userRepository.findById(cart.getUserId()).orElse(null));
		cartEntity.setAmount(cart.getAmount());
		cartEntity.setStatus(cart.getStatus());
		return cartEntity;
	}
	
	
}
