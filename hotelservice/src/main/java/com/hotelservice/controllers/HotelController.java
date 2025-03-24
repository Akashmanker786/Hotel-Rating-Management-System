package com.hotelservice.controllers;

import com.hotelservice.entities.Hotel;
import com.hotelservice.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
 class HotelController {

    @Autowired
    HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> addHotel(@RequestBody Hotel hotel){
        return new ResponseEntity<>(hotelService.createHotel(hotel), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotel(@PathVariable("id") String id){
        return new ResponseEntity<>(hotelService.getHotel(id),HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getHotels(){
        return new ResponseEntity<>(hotelService.getHotels(),HttpStatus.FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable("id") String id){
        try {
            hotelService.deleteHotel(id);
            return new ResponseEntity<>("User Deleted Successfully",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("User Not Found For This Id :"+id,HttpStatus.NOT_FOUND);
        }
    }

}
