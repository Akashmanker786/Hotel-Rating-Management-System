package com.userservice.controllers;
import com.userservice.entity.User;
import com.userservice.payload.ApiResponse;
import com.userservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
       return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAll(),HttpStatus.FOUND);
    }

    int retryCount=1;

    @GetMapping("/{id}")
//    @CircuitBreaker(name = "ratingHotelCircuitBreaker" , fallbackMethod = "ratingHotelServiceFallback")
//    @Retry(name = "ratingHotelCircuitRetry",fallbackMethod = "ratingHotelServiceFallback")
    @RateLimiter(name = "ratingHotelRateLimiter",fallbackMethod = "ratingHotelServiceFallback")
    public ResponseEntity<User> getById(@PathVariable("id") String id){
        log.info("Api Called and Retry Count is = {}",retryCount++);
        return new ResponseEntity<>(userService.getById(id),HttpStatus.FOUND);
    }

//    fallback method called if the rating or hotel service is either down
    public ResponseEntity<User> ratingHotelServiceFallback(String id , Exception e) {
        log.info("FALLBACK METHOD CALLED BECAUSE EIHTER RATING OR HOTEL SERVICE IS DOWN...",e.getMessage());
        User user = User.builder().userId("dummyId").userAbout("dummyAbout").userEmail("dummyEmail").userName("dummyName").build();
        return new ResponseEntity<User>(user,HttpStatus.SERVICE_UNAVAILABLE);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") String id){
        userService.removeUser(id);
        ApiResponse apiResponse = ApiResponse.builder().message("User deleted").status(HttpStatus.OK).build();
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }


}
