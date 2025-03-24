package com.userservice.serviceImpl;

import com.userservice.entity.Hotel;
import com.userservice.entity.Rating;
import com.userservice.entity.User;
import com.userservice.exceptions.ResourceNotFoundExp;
import com.userservice.repositories.UserRepo;
import com.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepository;

    @Autowired
    RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public List<User> getAll() {

        // getting the ratings data of a particular user by calling the ratings's api


        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return  userRepository.save(user);
    }

    @Override
    public void removeUser(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getById(String id) {

        User user =  userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExp("No User Found On Server For Given Id :"+id));

        // getting the ratings data of a particular user by calling the ratings's api
        Rating[] Ratings = restTemplate.getForObject("http://RATING-SERVICE/rating/user/"+user.getUserId(),Rating[].class);

//        converting into list
        List<Rating> RatingsList = Arrays.stream(Ratings).collect(Collectors.toList());

        // Now calling the hotels api using hotel id  taken from ratings's object to get the hotel details

        List<Rating> ratingWithHotelList = RatingsList.stream().map(rating -> {
            //        calling api
            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());


//        setting data into user object
          user.setRatings(ratingWithHotelList);

//        return user object
        return user;
    }





    // update method ---todo


}
