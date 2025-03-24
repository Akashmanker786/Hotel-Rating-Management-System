package com.ratingservice.controllers;

import com.ratingservice.entities.Rating;
import com.ratingservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rating")
public class MyController {

    @Autowired
    RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings(){
        return  ResponseEntity.status(HttpStatus.FOUND).body(ratingService.getAllRatings());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getAllRatingsByUserId(@PathVariable("userId") String userId){
        return ResponseEntity.status(HttpStatus.FOUND).body(ratingService.getAllRatingsByUserId(userId));
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getAllRatingsByHotelId(@PathVariable("hotelId") String hotelId){
        return ResponseEntity.status(HttpStatus.FOUND).body(ratingService.getAllRatingsByHotelId(hotelId));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteRating(String ratingId){
        try {
            ratingService.deleteRating(ratingId);
            return ResponseEntity.status(HttpStatus.OK).body("Rating delete success");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ratings not found for given id on server : "+ratingId);
        }


    }



}
