package com.cloudbees.trainbooking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "booking")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private int userId;
    private int scheduleId;
    private int sectionId;
    private int seatNumber;

}
