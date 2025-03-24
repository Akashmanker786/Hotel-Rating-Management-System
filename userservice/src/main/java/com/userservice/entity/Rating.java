package com.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    String ratingId;
    String userId;
    String hotelId;
    int rating;
    String feedBack;
    Hotel hotel;
}
