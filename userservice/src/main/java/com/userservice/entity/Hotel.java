package com.userservice.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hotel {
    String hotelId;

    String hotelName;

    String hotelLocation;

    String hotelAbout;
}
