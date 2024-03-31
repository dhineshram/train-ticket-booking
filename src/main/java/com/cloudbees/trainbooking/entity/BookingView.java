package com.cloudbees.trainbooking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "booking_view")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingView implements Serializable {

    @Id
    private int bookingId;
    private int trainNumber;
    private String fromStation;
    private String toStation;
    private String sectionName;
    private int seatNumber;
    private LocalDate date;
    private LocalTime startTime;
    private String name;
    private String email;
    private int ticketFare;

}
