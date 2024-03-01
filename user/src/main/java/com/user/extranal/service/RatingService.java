package com.user.extranal.service;

import com.user.entity.Rating;
import lombok.Builder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Service
@FeignClient(name="RATING-SERVICE")
public interface RatingService {
    @PostMapping("/api/ratings")
    public Rating createRating(Rating rating);

    @PutMapping("/api/ratings/{ratingId}")
    public Rating updateRating(@PathVariable("ratingId") String ratingId, Rating rating );
}
