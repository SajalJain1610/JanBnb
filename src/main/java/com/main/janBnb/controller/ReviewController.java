package com.main.janBnb.controller;

import com.main.janBnb.entity.User;
import com.main.janBnb.service.ReviewService;
import com.main.janBnb.payload.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/main/review")
public class ReviewController {

    @Autowired
    private  ReviewService reviewService;


    @PostMapping("/addReview/{propertyId}")
    public ResponseEntity<ReviewDto> addReview(
            @RequestBody ReviewDto dto,
            @AuthenticationPrincipal User user,
            @PathVariable String propertyId) {
         ReviewDto reviewDto = reviewService.addReview(dto, user, propertyId);
        return new ResponseEntity<>(reviewDto, HttpStatus.CREATED);
    }

    @PostMapping("/updateReview/{propertyId}")
    public ResponseEntity<ReviewDto> updateReview(
            @RequestBody ReviewDto dto,
            @AuthenticationPrincipal User user,
            @PathVariable String propertyId) {
        ReviewDto reviewDto = reviewService.updateReview(dto, user, propertyId);
        return new ResponseEntity<>(reviewDto, HttpStatus.CREATED);
    }


    @DeleteMapping("/deleteReview/{reviewId}")
    public ResponseEntity<String> deleteReview(
            @PathVariable String reviewId) {
         String msg = reviewService.deleteReview(reviewId);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }


    @GetMapping("/getReviewByUser")
    public ResponseEntity<List<ReviewDto>> getReviewByUser(
            @AuthenticationPrincipal User user) {
         List<ReviewDto> listReviewDto = reviewService.getReviewByUser(user);
        return new ResponseEntity<>(listReviewDto, HttpStatus.CREATED);
    }

}
