package com.hotelservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/workers")
public class WorkerController {

    List<String> workers = new ArrayList<>(Arrays.asList("Abdul","Sumit","Karan","Raju"));

    @GetMapping
    public ResponseEntity<List<String>> getStaffDetails(){
        return ResponseEntity.status(HttpStatus.FOUND).body(workers);
    }


}
