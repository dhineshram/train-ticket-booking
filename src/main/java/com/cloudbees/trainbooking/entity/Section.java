package com.cloudbees.trainbooking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "section")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Section implements Serializable {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private int id;
    private String name;
    private int trainNumber;
    private int totalSeats;

}
