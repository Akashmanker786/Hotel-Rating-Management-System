package com.hotelservice.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Hotel {
    @Id
    String hotelId;

    String hotelName;

    String hotelLocation;

    String hotelAbout;

}
