package com.cloudbees.trainbooking.controller;

import com.cloudbees.trainbooking.entity.BookingView;
import com.cloudbees.trainbooking.exception.NotFoundException;
import com.cloudbees.trainbooking.service.BookingViewService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookingViewController {

    private final BookingViewService service;

    public BookingViewController(BookingViewService service) {
        this.service = service;
    }

    @GetMapping("/bookings/train/{trainNumber}/section/{sectionId}")
    @Operation(summary = "API for - #7",
            description = "7. An API that lets you view the users and seat they are allocated by the requested section")
    public ResponseEntity<List<BookingView>> getAllBookings(@PathVariable int trainNumber, @PathVariable int sectionId) throws NotFoundException {
        var list = service.findAllWith(trainNumber, sectionId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
