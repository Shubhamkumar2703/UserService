package com.user.service.UserService.services.impl;

import com.user.service.UserService.entities.Hotel;
import com.user.service.UserService.entities.Rating;
import com.user.service.UserService.entities.User;
import com.user.service.UserService.exceptions.ResourceNotFoundException;
import com.user.service.UserService.external.services.HotelService;
import com.user.service.UserService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.user.service.UserService.repositories.UserRepository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        // generate unique userid
       String randomUserId = UUID.randomUUID().toString();
       user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        //implement RATING SERVICE call: using restTemplate
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {

        //get user from database with the help of user repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user with given id is not found on server !! :" +userId));

        //fetch rating of the above user from RATING SERVICE

       Rating[] ratingsOfUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+user.getUserId(), Rating[].class);
       logger.info("{} ",ratingsOfUser);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
           //api call to hotel service to get the hotel
           //http://localhost:8082/hotels/677656b3-be72-42b1-988f-754ad7a4877d

//           ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
           Hotel hotel = hotelService.getHotel(rating.getHotelId());
//           logger.info("response status code: {} " ,forEntity.getStatusCode());

           //set the hotel to rating
           rating.setHotel(hotel);

           //return the rating

           return rating;

       }).collect(Collectors.toList());

       user.setRatings(ratingList);

        return user;
    }
}
