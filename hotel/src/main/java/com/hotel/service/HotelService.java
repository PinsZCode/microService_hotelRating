package com.hotel.service;

import com.hotel.entity.Hotel;
import com.hotel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface HotelService {

    Hotel create(Hotel hotel);

    List<Hotel>getAllHotel();

    Hotel getHotelById(String id);
}

