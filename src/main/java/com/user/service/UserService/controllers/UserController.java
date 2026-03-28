package com.user.service.UserService.controllers;

import com.user.service.UserService.entities.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.user.service.UserService.services.UserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){


        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);


    }
    int retryCount=1;
    @GetMapping("/{userId}")
//    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//    @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
       log.info("Get Single user Handler: UserController");
        log.info("Retry count: {}", retryCount);
        retryCount++;
        User user = userService.getUser(userId);
        return  ResponseEntity.ok(user);
    }

    //creating fall back method for circuitBreaker



    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
//        log.info("Fallback is executed because services is down : {}", ex.getMessage());

        User user = User.builder()
                .email("dummy@gmail.com")
                .name("Dummy")
                .about("This user is created dummy because some services are down.")
                .userId("123456")
                .build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }


    //all user get
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser = userService.getAllUser();
        return  ResponseEntity.ok(allUser);
    }
}
