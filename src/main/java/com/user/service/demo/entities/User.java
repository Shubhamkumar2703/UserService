package com.user.service.demo.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
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


}
