package com.crewdeva.Crewdeva.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "user_table")
@Entity
public class User {
    @Id
    private UUID id;
    private String name;
    private String surname;
    private String userPicture;
    private String email;
    private String gender;
    private String locale;
    private LocalDateTime lastVisit;
}