package com.userservice.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    String userId;

    String userName;

    String userEmail;

    String userAbout;

    @Transient
    List<Rating> ratings = new ArrayList<>();
}
