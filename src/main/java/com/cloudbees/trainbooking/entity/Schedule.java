package com.cloudbees.trainbooking.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "schedule")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Schedule implements Serializable {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private int id;
    private int trainNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @DateTimeFormat(pattern = "hh:mm")
    private LocalTime startTime;
    private int ticketFare;
    private int availableSeats;

}
