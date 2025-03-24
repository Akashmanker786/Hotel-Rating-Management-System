package com.hotelservice.services;

import com.hotelservice.entities.Hotel;

import java.util.List;

public interface HotelService {

    public Hotel createHotel(Hotel hotel);

    public List<Hotel> getHotels();

    public Hotel getHotel(String id);

    public void deleteHotel(String id);

}
