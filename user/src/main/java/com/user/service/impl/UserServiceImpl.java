package com.user.service.impl;

import com.user.config.RestTemplateConfig;
import com.user.entity.Hotel;
import com.user.entity.Rating;
import com.user.entity.User;
import com.user.exceptions.ResourceNotFoundException;
import com.user.extranal.service.HotelService;
import com.user.repository.UserRepository;
import com.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplateConfig restTemplate;
    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {

        return userRepository.findAll();

    }

    @Override
    public User getUser(String userId) {
        //get user form data base with help og userRepo

        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("user with given id is not found "+ userId));


        Rating[] ratingOfUser = restTemplate.getRestTemplate().getForObject("http://RATING-SERVICE/api/ratings/users/" + user.getUserId(), Rating[].class);


        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();


        List<Rating> ratingList = ratings.stream().map(rating-> {
                //get rating of above user from rating service
                //http://localhost:8092/api/ratings/users/09511067-3f5a-4648-b6a6-5e3ddef6e5b6
             //   ResponseEntity<Hotel> forEntity = restTemplate.getRestTemplate().getForEntity("http://HOTEL-SERVICE/api/hotels/" + rating.getHotelId(), Hotel.class);
            //  Hotel hotel = forEntity.getBody();

            Hotel hotel = hotelService.getHotel(rating.getHotelId());

                //Set the hotel to the rating
                rating.setHotel(hotel);
                //return the rating
                return rating;

            }).collect(Collectors.toList());

        user.setRating(ratingList);
        return user;
    }
}
