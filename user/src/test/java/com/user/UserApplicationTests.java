package com.user;

import com.user.entity.Rating;
import com.user.extranal.service.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserApplicationTests {

	@Test
	void contextLoads() {
	}
//	@Autowired
//	private RatingService ratingService;
//	@Test
//	void createRating(){
//		Rating r = Rating.builder().rating(10).userId("").hotelId("").feedback("just looking like a wow").build();
//		ratingService.createRating(r);
//		System.out.println("created");
//	}

}
