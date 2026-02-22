package com.user.service.demo.services;

import com.user.service.demo.entities.User;

import java.util.List;

public interface UserService {


    User saveUser(User user);

    List<User> getAllUser();

    User getUser(String userId);

    //TODO: delete
    //TODO: update

}
