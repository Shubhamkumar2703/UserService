package com.user.service.UserService.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "micro_users")
public class User {

    @Id
    @Column(name = "Id")
    private String userId;

    @Column(name = "NAME", length = 20)
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ABOUT")
    private String about;

    @Transient
    private List<Rating> ratings  = new ArrayList<>();

    public User(String userId, String name, String email, String about, List<Rating> ratings) { 
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.about = about;
        this.ratings = ratings;
    }
}
