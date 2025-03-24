package com.ratingservice.service;

import com.ratingservice.entities.Rating;

import java.util.List;

public interface RatingService {

    public Rating create(Rating rating);

    public List<Rating> getAllRatings();

    public List<Rating> getAllRatingsByUserId(String userId);

    public List<Rating> getAllRatingsByHotelId(String hotelId);

    public void deleteRating(String ratingId);

}
