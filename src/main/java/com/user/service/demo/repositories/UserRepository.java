package com.user.service.demo.repositories;

import com.user.service.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {


}
