package com.jpmc.todo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "user_profiles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileEntity implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String fullName;

    private String gender;

    private LocalDate dob;

    @OneToOne
    private UserEntity user;

}
