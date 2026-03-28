package com.user.service.UserService.external.services;

import com.user.service.UserService.entities.Rating;
import jakarta.ws.rs.PUT;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.service.annotation.PutExchange;

@FeignClient(name = "RATINGSERVICE")
public interface RatingService {

    //GET

    //POST

    @PostMapping("/ratings")
    public Rating createRating(Rating values);

    //PUT
    @PutMapping("/ratings/{ratingId}")
    public Rating updateRating(@PathVariable("ratingId") String ratingId, Rating rating);

    @DeleteMapping("/ratings/{ratingId}")
    public void deleteRating(@PathVariable String ratingId);



}
