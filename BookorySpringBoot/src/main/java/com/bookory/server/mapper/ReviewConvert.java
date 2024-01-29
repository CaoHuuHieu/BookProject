package com.bookory.server.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bookory.models.response.ReviewResponseDTO;
import com.bookory.entity.ReviewEntity;

@Component
public class ReviewConvert {
	public ReviewResponseDTO toReviewResponseDTO(ReviewEntity reviewEntity) {
		ReviewResponseDTO review = new ReviewResponseDTO();
		review.setFullName(reviewEntity.getUserEntity().getFullName());
		review.setAvatar(reviewEntity.getUserEntity().getAvatar());
		review.setStar(reviewEntity.getStar());
		review.setCreateDate(reviewEntity.getCreateDate());
		review.setComment(reviewEntity.getComment());
		return review;
	}
	public List<ReviewResponseDTO> toReviewResponseDTO(List<ReviewEntity> reviewEntities) {
		List<ReviewResponseDTO>  reviews = new ArrayList<>();
		for(ReviewEntity reviewEntity:reviewEntities) {
			reviews.add(toReviewResponseDTO(reviewEntity));
		}
		return reviews;
	}
}
