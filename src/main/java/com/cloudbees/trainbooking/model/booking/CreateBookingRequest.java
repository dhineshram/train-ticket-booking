package com.cloudbees.trainbooking.model.booking;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
public class CreateBookingRequest extends UpdateBookingRequest implements Serializable {

    private int userId;
    private int scheduleId;

}
