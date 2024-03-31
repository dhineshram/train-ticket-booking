package com.cloudbees.trainbooking.service;

import com.cloudbees.trainbooking.entity.BookingView;
import com.cloudbees.trainbooking.exception.NotFoundException;

import java.util.List;

public interface BookingViewService {

    BookingView get(int bookingId) throws NotFoundException;

    List<BookingView> findAllWith(int trainNumber, int sectionId) throws NotFoundException;
}
