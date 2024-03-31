package com.cloudbees.trainbooking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Entity
@Table(name = "train")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Train implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private int number;
    private String name;
    private String fromStation;
    private String toStation;

}
