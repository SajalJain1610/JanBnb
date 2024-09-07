package com.main.janBnb.service;

import com.main.janBnb.entity.User;
import com.main.janBnb.payload.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto addReview(ReviewDto dto, User user, String propertyId);

    ReviewDto updateReview(ReviewDto dto, User user, String propertyId);


    String deleteReview(String reviewId);

    List<ReviewDto> getReviewByUser(User user);
}
