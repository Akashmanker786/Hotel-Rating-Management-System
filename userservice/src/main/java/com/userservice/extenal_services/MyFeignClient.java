package com.userservice.extenal_services;

import com.userservice.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface MyFeignClient {

    @GetMapping("/hotel/{id}")
    public Hotel getHotel(@PathVariable("id") String id);

}
