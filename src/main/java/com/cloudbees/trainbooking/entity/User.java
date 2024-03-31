package com.cloudbees.trainbooking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "user_details")
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private String emailId;

}
