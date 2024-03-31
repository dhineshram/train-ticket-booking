package com.cloudbees.trainbooking.model.booking;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
public class UpdateBookingRequest implements Serializable {

    private int sectionId;
    private int seatNumber;

}
