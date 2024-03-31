package com.cloudbees.trainbooking.service;

import com.cloudbees.trainbooking.entity.Booking;
import com.cloudbees.trainbooking.entity.BookingView;
import com.cloudbees.trainbooking.exception.CustomException;
import com.cloudbees.trainbooking.exception.NotFoundException;
import com.cloudbees.trainbooking.model.booking.CreateBookingRequest;
import com.cloudbees.trainbooking.model.booking.UpdateBookingRequest;

public interface BookingService extends GenericService<Booking> {
    BookingView getView(int bookingId) throws NotFoundException;

    BookingView create(CreateBookingRequest request) throws CustomException;

    BookingView update(int id, UpdateBookingRequest request) throws CustomException;

    void delete(int id) throws NotFoundException;
}
